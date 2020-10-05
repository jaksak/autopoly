package pl.longhorn.autopoly.board.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.BoardListener;
import pl.longhorn.autopoly.board.BoardRepository;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.BoardFinishedLogContent;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.PlayerInBoardQuery;

@Service
@RequiredArgsConstructor
public class DeleteBoardCommand {

    private final BoardRepository repository;
    private final BoardLogCommand boardLogCommand;
    private final BoardQuery boardQuery;
    private final PlayerInBoardQuery playerInBoardQuery;
    private final BoardListener boardListener;

    public void deleteBoard() {
        prepareLog();
        repository.save(null);
        boardListener.afterBoardDelete();
    }

    private void prepareLog() {
        var board = boardQuery.get();
        var playerInBoard = playerInBoardQuery.get();
        boardLogCommand.add(new BoardFinishedLogContent(board.getId(), playerInBoard.getTheBest().map(Player::getId).orElseThrow()), board.getId());
    }
}
