package elak.readinghood.backend.threads;

import elak.readinghood.backend.posts.Post;
import elak.readinghood.backend.posts.Posts;
import elak.readinghood.backend.server.ServerRequest;
import elak.readinghood.backend.tags.Tags;
import elak.readinghood.backend.profiles.Profile;
import elak.readinghood.backend.server.ServerUpdate;

/**
 * @author Spiros
 */
public class Thread {
    private int id, views;
    private String title;
    private Tags tags;
    private Post questionPost;
    private Posts answerPosts;

    /**
     * Trivial constructor
     *
     * @param id           is the id of the thread
     * @param title        is the title of the thread
     * @param views        are the amount of views of the thread
     * @param tags         are the tags of the thread
     * @param questionPost the question post of the thread
     * @param answerPosts  are the answerPosts of the thread
     */
    public Thread(int id, String title, int views, Tags tags, Post questionPost, Posts answerPosts) {
        this.id = id;
        this.views = views;
        this.title = title;
        this.tags = tags;
        this.questionPost = questionPost;
        this.answerPosts = answerPosts;
    }

    /**
     * @return the user that created the thread
     */
    public Profile getThreadCreatorProfile() {
        return questionPost.getAuthor();
    }

    /**
     * YOU NEVER USE THIS FUNCTION as front end developer.
     *
     * @return the id of the thread
     */
    public int getId() {
        return id;
    }

    /**
     * @return the views of the thread
     */
    public int getViews() {
        return views;
    }

    /**
     * @return the title of the thread
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the tags of the thread
     */
    public Tags getTags() {
        return tags;
    }

    /**
     * @return the question post of the creator the thread
     */
    public Post getQuestionPost() {
        return questionPost;
    }

    /**
     * @return the answerPosts of the thread
     */
    public Posts getAnswerPosts() {
        return answerPosts;
    }

    /**
     * This function add this thread to the favorites of the user.
     *
     * @return a boolean value which indicates if the addition of this thread to favorites was successful
     */
    public boolean addToFavorites() {
        return ServerUpdate.addThreadToFavorites(id);
    }

    /**
     * This function answers a thread and returns an error text.
     * <p>
     * Error0 = "Success"
     * Error1 = "Error connecting with server"
     * Error2 = "Fill the fields"
     *
     * @param text is the text of the post that you are gonna write
     * @return an error text
     */
    public String answerThreadWithAPost(String text) {
        boolean textFullOfSpaces = text.replaceAll("\\s+", "").isEmpty();
        if (text.isEmpty() || textFullOfSpaces) {
            return "Fill the fields";
        }

        if (ServerUpdate.answerThread(id, text)) {
            answerPosts.addPost(ServerRequest.getLatestPostOfAThread(id));
            return "Success";
        } else {
            return "Error connecting with server";
        }
    }

    /**
     * NEVER USE THIS FUNCTION.
     * This function increase the views of a thread.
     */
    public void increaseViews() {
        views++;
    }

    /**
     * @return the latest added answer post
     */
    public Post getTheLatestAddedPost() {
        return answerPosts.getPost(answerPosts.size() - 1);
    }
}