public class P7_Dhruva_Krupa_YelpRating implements Comparable<P7_Dhruva_Krupa_YelpRating> {
    private String target;
    private String review;
    private String userName;
    private double rating;

    public P7_Dhruva_Krupa_YelpRating(
            String target, String review, double rating, String userName) {
        this.target = target;
        this.review = review;
        this.userName = userName;
        this.rating = 0.0;
        setRating(rating);
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating < 0.0 || rating > 5.0) {
            return;
        }

        this.rating = rating;
    }

    @Override
    public int compareTo(P7_Dhruva_Krupa_YelpRating other) {
        return Double.compare(rating, other.rating);
    }

    @Override
    public String toString() {
        return String.format(
                "Target:\t%s%nReview:\t%s%nRating:\t%f%nUser:\t%s%n",
                target, review, rating, userName);
    }
}
