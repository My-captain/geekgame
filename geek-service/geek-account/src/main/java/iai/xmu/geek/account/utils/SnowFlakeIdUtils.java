package iai.xmu.geek.account.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 1(默认用0，表示id是正数) + 1() + 40 + 5 + 5 + 12 = 64 bits
 */
@Component
public class SnowFlakeIdUtils {

    // 2015-01-01
    private final long START_DATE = 1420041600000L;
    private final long NUM_WORKER_ID_BITS = 4L;
    // 序列号位数：6， 一个毫秒单位内可单个实例承受并发64个ID
    private final long NUM_SEQUENCE_ID_BITS = 6L;

    private final long NUM_WORKER_ID_SHIFTS = NUM_SEQUENCE_ID_BITS;
    private final long NUM_TIMESTAMP_ID_SHIFTS = NUM_SEQUENCE_ID_BITS + NUM_WORKER_ID_BITS;
    private final long SEQUENCE_MASK = ~(-1L << NUM_SEQUENCE_ID_BITS);

    @Value("${worker-id}")
    private long workerId;
    private long sequenceId=0L;
    private long lastTimestamp=-1L;

    public synchronized String getSnowflakeId() {
        long time_stamp = getCurrentTimestamp();
        // TODO: 优化时钟回拨错误
        if ( time_stamp < lastTimestamp ) {
            throw new RuntimeException(
                    String.format("clock moved backward! Failed to generate Snowflake ID for %d millisecond...", lastTimestamp - time_stamp)
            );
        }
        // 如果是同一时间生成，则序列号+1
        if ( lastTimestamp == time_stamp ) {
            sequenceId = (sequenceId + 1) & SEQUENCE_MASK;
            // 序列溢出
            if ( sequenceId == 0 ) {
                time_stamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 否则，从0开始
            sequenceId = 0L;
        }
        lastTimestamp = time_stamp;
        return String.format("%13s", Long.toHexString(
                (time_stamp - START_DATE) << NUM_TIMESTAMP_ID_SHIFTS
                        | (workerId << NUM_WORKER_ID_SHIFTS)
                        | sequenceId)
        ).replace(' ', '0');
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp
     * @return
     */
    protected long tilNextMillis(long lastTimestamp) {
        long time_stamp = getCurrentTimestamp();
        while ( time_stamp <= lastTimestamp ) {
            time_stamp = getCurrentTimestamp();
        }
        return time_stamp;
    }

    protected long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
}

