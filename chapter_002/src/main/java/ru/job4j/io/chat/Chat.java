package ru.job4j.io.chat;

import java.io.*;

/**
 * Chat with bot.
 */
public class Chat {
    /**
     * the bot from chat
     */
    final private Bot bot;
    /**
     * the logger from chat
     */
    final private Logger logger;

    /**
     * buffered input stream reader
     */
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param argStore - contains init arguments from Chat
     */
    public Chat(ArgStore argStore) {
        this.bot = new BotImpl(argStore.knowledgePath());
        this.logger = new LoggerImpl(argStore.logPath());
    }

    /**
     * Start Chat application
     *
     * @throws IOException - exception
     */
    public void start() throws IOException {
        final String helloMsg = "Привет, я бот. Пообщаемся?";
        if (!logger.valid()) {
            println("System: log file not specified, logger disabled");
        }
        println(helloMsg);
        logger.write(helloMsg);
        String message = getUserMessage();
        while (checkMessage(message)) {
            String answer = bot.answer(message);
            println(answer);
            logger.write(message);
            logger.write(answer);
            message = getUserMessage();
        }
        logger.close();
    }

    /**
     * @return - user message
     * @throws IOException - io exception
     */
    private String getUserMessage() throws IOException {
        System.out.print("$>");
        return in.readLine();
    }

    /**
     * @param message - show any message
     */
    private void println(String message) {
        System.out.println(message);
    }

    /**
     * @param message - check exit command
     * @return - false - end work chat
     */
    private boolean checkMessage(String message) {
        return !message.toLowerCase().equals("закончить");
    }

    /**
     * @param args - command line args
     * @throws IOException - io exception
     */
    public static void main(String[] args) throws IOException {
        String[] args1 = new String[]{"chapter_002/src/main/resources/knowledgeBase.txt", "chapter_002/src/main/resources/logChat.txt"};
        new Chat(new ArgStoreImpl(args1)).start();
    }
}
