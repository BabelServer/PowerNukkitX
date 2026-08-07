package org.powernukkitx.scoreboard;

import org.cloudburstmc.protocol.bedrock.data.payload.scoreboard.ScoreInfo;
import org.powernukkitx.scoreboard.scorer.IScorer;

public interface IScoreboardLine {

    IScorer getScorer();

    long getLineId();

    IScoreboard getScoreboard();

    long getScore();

    boolean setScore(long score);

    default boolean addScore(long addition) {
        return setScore(getScore() + addition);
    }

    default boolean removeScore(long reduction) {
        return setScore(getScore() - reduction);
    }

    default ScoreInfo toNetworkInfo() {
        return getScorer().toNetworkInfo(getScoreboard(), this);
    }

    default void updateScore() {
        getScoreboard().updateScore(this);
    }
}
