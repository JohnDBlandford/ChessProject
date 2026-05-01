package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import Board.Board;
import Game.Replay;
import Pieces.Piece;

public class GUIreplay extends JPanel {

    Replay replay;
    private static final int TILE_SIZE = 80;
    private static final int BOARD_SIZE = 8;
    private Map<String, Image> pieceImages = new HashMap<>();

    public GUIreplay(Replay replay) {

        this.replay = replay;
        setPreferredSize(new Dimension(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE));
        loadImages();

    }

    public static void start(Replay replay) {

        replay.startReplay();

        JFrame frame = new JFrame("Chess Replay");

        GUIreplay boardPanel = new GUIreplay(replay);

        JButton nextMoveButton = new JButton("Next Move -->");
        JButton previousMoveButton = new JButton("<-- Previous Move");
        JLabel label = new JLabel();

        nextMoveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!replay.moveForward()) {
                    label.setText("End of game");
                    boardPanel.repaint();
                } else {
                    boardPanel.repaint();
                }
            }
        });

        previousMoveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!replay.moveBackwards()) {
                    label.setText("Start of game");
                    boardPanel.repaint();
                } else {
                    boardPanel.repaint();
                }

            }
        });

        JPanel buttonHolder = new JPanel();

        buttonHolder.add(nextMoveButton, BorderLayout.EAST);
        buttonHolder.add(previousMoveButton, BorderLayout.WEST);
        buttonHolder.add(label, BorderLayout.CENTER);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(buttonHolder, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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
        Board board = replay.getBoard();
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
                if (currentPiece != null) {
                    Image img = pieceImages.get(currentPiece.getColor() + "_" + currentPiece.getPieceType());
                    g.drawImage(img, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                }

            }

        }

    }

}
