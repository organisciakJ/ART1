package project;

import java.util.ArrayList;

public class Neuron {

    ArrayList<Float> v = new ArrayList<>();
    ArrayList<Float> w = new ArrayList<>();

    Float match;
    Float similarity;

    @Override
    public String toString() {
        return "Neuron{match=" + match + "}";
    }


    public Neuron(float pixelCount) {
        for (int i = 0; i < pixelCount; i++) {
            this.w.add(1.0f/(1.0f+pixelCount));
            this.v.add(1f);
        }
        System.out.println(this.w.get(1));
    }

    /**
     *
     * @param currentImg
     */
    public void calcMatch(int[] currentImg) {
        this.match = 0f;

        for (int i = 0; i < currentImg.length; i++){
            this.match += currentImg[i] * this.w.get(i);
        }
        System.out.println("match:" + this.match);
    }


    /**
     *
     * @param currentImg
     */
    public void calcSimilarity(int[] currentImg) {
        this.similarity = 0f;
        float similarityBottom = 0;

        for (int i = 0; i < currentImg.length; i++){
            this.similarity += currentImg[i] * this.v.get(i);
            similarityBottom += currentImg[i];
        }

        this.similarity /= similarityBottom;
        System.out.println("Similarity: " + similarity);

    }


    /**
     *
     * @param currentImg
     */
    public void adaptation(int [] currentImg) {
        float sumBottom = 0.5f;

        for (int i = 0; i < this.v.size(); i++) {
            sumBottom += this.v.get(i) * currentImg[i];
        }

        for (int i = 0; i < this.v.size(); i++) {
            this.v.set(i, this.v.get(i) * currentImg[i]);
            this.w.set(i, this.v.get(i) / sumBottom);
        }

    }

    public Float getMatch() {
        return match;
    }

    public void setMatch(Float match) {
        this.match = match;
    }

    public Float getSimilarity() {
        return similarity;
    }

}
