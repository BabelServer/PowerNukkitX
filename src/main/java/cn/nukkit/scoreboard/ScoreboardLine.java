package cn.nukkit.scoreboard;

import cn.nukkit.Server;
import cn.nukkit.event.scoreboard.ScoreboardLineChangeEvent;
import cn.nukkit.scoreboard.scorer.IScorer;
import lombok.Getter;


@Getter
public class ScoreboardLine implements IScoreboardLine{

    protected static long staticLineId = 0;

    protected final IScoreboard scoreboard;
    protected final IScorer scorer;
    protected final long lineId;
    protected long score;

    public ScoreboardLine(IScoreboard scoreboard, IScorer scorer) {
        this(scoreboard, scorer, 0);
    }

    public ScoreboardLine(IScoreboard scoreboard, IScorer scorer, long score) {
        this.scoreboard = scoreboard;
        this.scorer = scorer;
        this.score = score;
        this.lineId = ++staticLineId;
    }

    @Override
    public boolean setScore(long score) {
        if (scoreboard.shouldCallEvent()) {
            var event = new ScoreboardLineChangeEvent(scoreboard, this, score, this.score, ScoreboardLineChangeEvent.ActionType.SCORE_CHANGE);
            Server.getInstance().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return false;
            }
            score = event.getNewValue();
        }
        this.score = score;
        updateScore();
        return true;
    }
}
