import javax.swing.*;

import Game.Game;
import Pieces.Piece;
import Board.Board;
import util.*;
import util.Color;

import java.awt.*;
import java.awt.event.*;

import java.util.HashMap;
import java.util.Map;

public class GUI extends JPanel {

    private static final long serialVersionUID = -8645634744350993870L;

    private static final int TILE_SIZE = 80;
    private static final int BOARD_SIZE = 8;
    private Image pieceImage;
    private int mouseX;
    private int mouseY;
    private int dragOffsetX;
    private int dragOffsetY;
    private boolean dragging = false;
    private Game game;
    private Map<String, Image> pieceImages = new HashMap<>();
    private int fromRow, fromCol;
    private JLabel moveLabel;
    private JPanel leftSidebar;
    private JPanel rightSidebar;

    public GUI(Game game, JLabel label, JPanel rightSidebar, JPanel leftSidebar) {

        this.leftSidebar = leftSidebar;
        this.rightSidebar = rightSidebar;

        this.moveLabel = label;

        this.game = game;
        setPreferredSize(new Dimension(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE));

        loadImages();

        MouseAdapter mouseHandler = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                fromRow = e.getY() / TILE_SIZE;
                fromCol = e.getX() / TILE_SIZE;

                Piece piece = game.getBoard().pieceAt(fromRow, fromCol);

                if (piece != null) {
                    dragging = true;
                    dragOffsetX = e.getX() - (fromCol * TILE_SIZE);
                    dragOffsetY = e.getY() - (fromRow * TILE_SIZE);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {

                if (dragging) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    repaint();

                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (dragging) {
                    // Snap to nearest tile
                    int newGridX = (e.getX() - dragOffsetX + TILE_SIZE / 2) / TILE_SIZE;
                    int newGridY = (e.getY() - dragOffsetY + TILE_SIZE / 2) / TILE_SIZE;

                    // prevent piece from going offscreen
                    dragging = false;
                    MoveResult result = game.tryMove(fromRow, fromCol, newGridY, newGridX);

                    switch (result) {
                        case SUCCESS:
                            moveLabel.setText("");
                            updateCaptureList();
                            repaint();
                            break;
                        case NO_PIECE:

                            repaint();
                            break;
                        case WRONG_TURN:
                            moveLabel.setText("Wrong Turn");
                            repaint();
                            break;
                        case ILLEGAL_MOVE:
                            moveLabel.setText("That piece cannot move like that");
                            repaint();
                            break;
                        case LEAVES_KING_IN_CHECK:
                            moveLabel.setText("Your King would be in check");
                            repaint();
                            break;
                        case CASTLE_ILLEGAL:
                            moveLabel.setText("You cannot castle like that");
                            repaint();
                            break;
                    }

                }
            }
        };

        addMouseListener(mouseHandler);

        addMouseMotionListener(mouseHandler);

    }

    private void loadImages() {

        pieceImages.put("WHITE_PAWN", new ImageIcon("src/resources/pieces/Chess_plt60.png").getImage());
        pieceImages.put("BLACK_PAWN", new ImageIcon("src/resources/pieces/Chess_pdt60.png").getImage());
        pieceImages.put("WHITE_KNIGHT", new ImageIcon("src/resources/pieces/Chess_nlt60.png").getImage());
        pieceImages.put("BLACK_KNIGHT", new ImageIcon("src/resources/pieces/Chess_ndt60.png").getImage());
        pieceImages.put("WHITE_BISHOP", new ImageIcon("src/resources/pieces/Chess_blt60.png").getImage());
        pieceImages.put("BLACK_BISHOP", new ImageIcon("src/resources/pieces/Chess_bdt60.png").getImage());
        pieceImages.put("WHITE_ROOK", new ImageIcon("src/resources/pieces/Chess_rlt60.png").getImage());
        pieceImages.put("BLACK_ROOK", new ImageIcon("src/resources/pieces/Chess_rdt60.png").getImage());
        pieceImages.put("WHITE_QUEEN", new ImageIcon("src/resources/pieces/Chess_qlt60.png").getImage());
        pieceImages.put("BLACK_QUEEN", new ImageIcon("src/resources/pieces/Chess_qdt60.png").getImage());
        pieceImages.put("WHITE_KING", new ImageIcon("src/resources/pieces/Chess_klt60.png").getImage());
        pieceImages.put("BLACK_KING", new ImageIcon("src/resources/pieces/Chess_kdt60.png").getImage());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Board board = game.getBoard();
        // Draws chessboard
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                if ((row + col) % 2 == 0) {
                    g.setColor(java.awt.Color.WHITE);

                } else {
                    g.setColor(java.awt.Color.GRAY);
                }

                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                Piece currentPiece = board.pieceAt(row, col);

                if (currentPiece != null && !(dragging && row == fromRow && col == fromCol)) {
                    pieceImage = pieceImages.get(currentPiece.getColor() + "_" + currentPiece.getPieceType());
                    g.drawImage(pieceImage, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);

                }
            }

        }
        if (dragging) {

            pieceImage = pieceImages.get(
                    board.pieceAt(fromRow, fromCol).getColor() + "_" + board.pieceAt(fromRow, fromCol).getPieceType());
            g.drawImage(pieceImage, mouseX - dragOffsetX,
                    mouseY - dragOffsetY, TILE_SIZE, TILE_SIZE, this);

        }

    }

    private void updateCaptureList() {

        leftSidebar.removeAll();
        rightSidebar.removeAll();

        for (Piece piece : game.getCapturedPieces(Color.WHITE)) {
            String key = piece.getColor() + "_" + piece.getPieceType();
            Image scaled = pieceImages.get(key);
            rightSidebar.add(new JLabel(new ImageIcon(scaled)));

        }

        for (Piece piece : game.getCapturedPieces(Color.BLACK)) {

            String key = piece.getColor() + "_" + piece.getPieceType();
            Image scaled = pieceImages.get(key);
            leftSidebar.add(new JLabel(new ImageIcon(scaled)));

        }

        leftSidebar.revalidate();
        leftSidebar.repaint();
        rightSidebar.revalidate();
        rightSidebar.repaint();

    }

}
