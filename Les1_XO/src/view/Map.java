package view;

import game.AI;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Класс отображения игрового поля. Наследуется от класса JPanel,
 * что позволяет настраивать игровое поле.
 */
public class Map extends JPanel {
    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;
    // Константа отступа от края ячейки (5 пикселей)
    private final static int DOT_PADDING = 5;
    private String winner;
    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!";
    private Game game;
    private  AI ai;

    public Map(){
        game = new Game();
        ai = new AI();
        game.initMap();
        game.setGameOver(false);
        game.setInitialized(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }
    /**
     * Запуск игры.
     * @param mode тип игры (пк-пк, человек-пк).
     * @param fSzX размер поля в ширину.
     * @param fSzY размер поля в высоту.
     * @param wLen количество повторений для победы.
     */
    void startNewGame(int mode, int fSzX, int fSzY, int wLen){
        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\nLength: %d",
                mode, fSzX, fSzY, wLen);
//        game = new Game();
//        ai = new AI();
        game.initMap();
        game.setGameOver(false);
        game.setInitialized(true);
        // Принудительно заставляем Swing перерисовать игровое поле.
        repaint();
    }
    private void update(MouseEvent e){
        // Проводим проверку окончена ли игра и инициализировано ли поле.
        if (game.isGameOver() || !game.isInitialized()) return;
        /*  Получаем координаты, где произошел щелчок мыши.
            Полученную координату делим на ширину ячейки.
            Тем самым получая индекс ячейки в которой произошел щелчок.
            Например: X=405, Y=400
                        405/185 = 2 (целочисленное деление (2,189))
                        400/169 = 2 (целочисленное деление (2,366))
         */
        int cellY = e.getX()/cellWidth;
        int cellX = e.getY()/cellHeight;
        System.out.printf("Ход игрока:  х=%d y=%d\n", cellX, cellY);

        // TODO Возможно объединить в единый метод
        if (!game.isValidCell(cellX, cellY) || !game.isEmptyCell(cellX, cellY)) return;

        // Игрок осуществляет ход
        game.move(cellX, cellY);
        // Заново перерисовываем поле.
        repaint();
        if (checkEndGame(MSG_WIN_HUMAN)) return;

        // Бот осуществляет ход
        ai.stepIi(game.getCharArr());
        repaint();
        if (checkEndGame(MSG_WIN_AI) ) return;
    }

    private boolean checkEndGame(String gamer){
        // Проверяем победу
        if (game.checkWin()) {
            game.setGameOver(true);
            winner = gamer;
            repaint();
            return true;
        }
        // Проверяем ничью
        if (game.isMapFull()){
            game.setGameOver(true);
            winner = MSG_DRAW;
            repaint();
            return true;
        }
        return false;
    }

    /**
     * Переопределенный метод отрисовки компонента (игрового поля).
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g){
        if (!game.isInitialized()) return;
        // Получаем ширину окна
        panelWidth = getWidth();
        // Получаем высоту окна
        panelHeight = getHeight();
        // Определяем высоту ячейки
        cellHeight = panelHeight / 7;
        // Определяем ширину ячейки
        cellWidth = panelWidth / 7;

        // Задаем цвет линии
        g.setColor(Color.BLACK);

        // Рисуем сетку поля для игры
        // Горизонтальные линии. Перебираем в цикле кол-во горизонтальных линии.
        for (int h = 0; h < 7; h++) {
            // Получаем координату на каком удалении от 0 точки по оси Y рисовать линию
            int y = h * cellHeight;
            /*  Рисуем линию от
                (0 координаты X, рассчитанной координаты Y) до
                (конца окна по координатам X, рассчитанной координате Y).
                При окне высотой - 555, шириной 507
                начальная точка - (0, 0) - конечная точка (507, 0)
                начальная точка - (0, 169) - конечная точка (507, 169)
                начальная точка - (0, 338) - конечная точка (507, 338)
             */
            g.drawLine(0, y, panelWidth, y);
        }
        // Вертикальные линии.
        for (int w = 0; w < 7; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        // Отрисовка фишек участников
        for (int y = 0; y < game.getCol(); y++) {
            for (int x = 0; x < game.getRow(); x++) {
                if (game.getCell(y, x) == ' ') continue;

                if (game.getCell(y, x) == 'X'){
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (game.getCell(y, x) == 'O') {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                }else {
                    throw new RuntimeException("Unexpected value " + game.getCell(y, x) +
                            " in cell: x=" + x + " y=" + y);
                }
            }
        }
        if (game.isGameOver()) showMessageGameOver(g);
    }

    /**
     * Вывод сообщения об окончании игры в текущем окне.
     * @param g
     */
    private void showMessageGameOver(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,200,getWidth(),70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        // TODO добавить тип победы
        switch (winner){
            case MSG_DRAW -> g.drawString(MSG_DRAW, 180, getHeight() / 2);
            case MSG_WIN_AI -> g.drawString(MSG_WIN_AI, 20, getHeight() /2);
            case MSG_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 70, getHeight() /2);
            default -> throw new RuntimeException("Unexpected gameOver state: ");
        }
    }

}
