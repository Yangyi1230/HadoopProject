import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileWriter;


/**
 * Author: Spikerman < mail4spikerman@gmail.com >
 * Created Date: 10/28/17
 */
public class StreamCrawler {
    private final static Logger logger = Logger.getLogger(StreamCrawler.class);
    private static final int retryLimit = 2;
    private static final String fileName = "tweets.txt";
    private static int workerId = 0;
    private static long newStreamInterval = 5000;
    private final Object lock = new Object();
    private ConfigurationBuilder cb;
    private FileWriter file;
    private TwitterStream twitterStream;
    private int retryTimes = 0;

    public StreamCrawler() {
        workerId++;
        boolean isFirst = (workerId & 1) == 0;
        logger.info("======= worker " + (workerId & 1) + " start work =======");

        try {
            file = new FileWriter(fileName, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cb = new ConfigurationBuilder();
        if (isFirst) {
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("aecbpbv7UX9kZf8k5klwsbGgY")
                    .setOAuthConsumerSecret("7PKfvEH8y81VITIxkpPbPFoJJLZEB97DJqC7nZBFqYLgY8ZUfQ")
                    .setOAuthAccessToken("914934419835715586-htd8tXW3gsJvZJDwSTFUXHlO48hORrN")
                    .setOAuthAccessTokenSecret("0tMWimLrLfCrLJx1K8wELgc4LDUyZGt9Zvx8fO1SHfTpY");

        } else {
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("W1wlfbPOIsHWM7HAZijxYNPW6")
                    .setOAuthConsumerSecret("K2sEP5PLAHdkupiXCLdV8cjbwOoPAyDGMe0kLQ27OnhHoM8pJ8")
                    .setOAuthAccessToken("3232249921-Czg0p4c5ca14t7UdkIZfEy6mOtQOkmN7tZnNZmU")
                    .setOAuthAccessTokenSecret("caB9Kwmx2p04cJIGflXLMpmOzHb1IkqCDLtUkq2e8s11V");
        }

    }

    public static void main(String args[]) {
        try {
            for (int i = 0; i < 2; i++) {
                long start = System.currentTimeMillis();
                StreamCrawler streamCrawler = new StreamCrawler();
                streamCrawler.start();
                while ((System.currentTimeMillis() - start) < StreamCrawler.newStreamInterval) ;

            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //construct tweet object from respond content
    private void handleState(Status status) {
        TweetInfo tweet = new TweetInfo();
        StringBuilder sb = new StringBuilder();
        tweet.id = status.getId();
        sb.append(tweet.id).append("\t");
        tweet.latitude = status.getGeoLocation().getLatitude();
        sb.append(tweet.latitude).append("\t");

        tweet.longitude = status.getGeoLocation().getLongitude();
        sb.append(tweet.longitude).append("\t");

        tweet.createdDate = status.getCreatedAt();
        sb.append(tweet.createdDate.toString()).append("\t");

        tweet.text = status.getText().replace("\n", "").replace("\r", "");
        sb.append(tweet.text).append("\t");

        tweet.userId = status.getUser().getId();
        sb.append(tweet.userId).append("\n");
        exportToFile(sb.toString());

        //System.out.println(tweet.createdDate);

        //System.out.println(tweet.id + " " + tweet.latitude + " " + tweet.longitude + " " + tweet.createdDate + " " + tweet.text);

    }

    private void stop() {
        twitterStream.cleanUp(); // shutdown internal stream consuming thread
        twitterStream.shutdown(); // shuts down internal dispatcher thread shared by all TwitterStream instances.
    }

    private void start() throws Exception {
        twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                if (status.getGeoLocation() != null) {
                    handleState(status);
                }
            }

            @Override
            public void onException(Exception ex) {
                if (retryTimes > retryLimit) {
                    logger.info("network error, connection close");
                    try {
                        file.close();
                    } catch (Exception e) {
                        logger.info("file close error");
                    }
                    if ((workerId & 1) == 0)
                        logger.info("======= work 0 complete, shut down current connection ======");
                    else
                        logger.info("======= work 1 complete, shut down current connection =======");
                    synchronized (lock) {
                        lock.notify();
                    }
                } else {
                    retryTimes++;
                    logger.info("network error, retry " + retryTimes + "th times");
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {

            }

            @Override
            public void onStallWarning(StallWarning arg0) {
                // TODO Auto-generated method stub
                System.out.println(arg0);
            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub
                System.out.println(arg0);
            }

        };
        twitterStream.addListener(listener);
        FilterQuery filterQuery = new FilterQuery();
        double[][] locations = {{-74, 40}, {-73, 41}}; //those are the boundary from New York City
        filterQuery.locations(locations);
        twitterStream.filter(filterQuery);
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop();
    }

    private void exportToFile(String s) {
        try {
            file.write(s);
            file.flush();// make sure buffered data is written into the disk
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
