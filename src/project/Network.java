package project;

import java.util.ArrayList;

public class Network {

    float p = 0.8f, similarity;
    int currentImg[], neuronId;
    ArrayList<Neuron> neuronList = new ArrayList<>();

    /**
     *
     * @param image
     */
    public void addToNeuronList(int[] image) {
        this.neuronList.add(new Neuron(image.length));
    }


    /**
     *
     * @param n
     * @return
     */
    public float getSim(Neuron n){
        float x = n.getSimilarity();
        return x;
    }


    public Neuron returnWinnerNeuron(int[] currentImg){

        for (Neuron n : neuronList) {
            n.calcMatch(currentImg);
        }

        for (int i = 0; i < neuronList.size(); i++) {
            Neuron n = this.neuronList.get(0);
            for (int j = 0; j < neuronList.size(); j++) {
                System.out.println("Aktualny " + j + " = " + neuronList.get(j));
                if (neuronList.get(j).getMatch() > n.getMatch()) {
                    n = neuronList.get(j);
                }
            }

            if (n.getMatch() == 0f){
                Neuron newN = new Neuron(currentImg.length);
                newN.adaptation(currentImg);
                this.neuronList.add(newN);
                System.out.println("Utworzono nowy neuron");
                neuronId = neuronList.indexOf(newN);
                return newN;
            }

            n.calcSimilarity(currentImg);
            similarity = getSim(n);

            if (n.getSimilarity() > this.p) {
                n.adaptation(currentImg);
                System.out.println("Zaakceptowano neuron");
                neuronId = neuronList.indexOf(n);
                return n;
            } else {
                n.setMatch(0f);
            }
        }

        Neuron newNeuron = new Neuron(currentImg.length);
        newNeuron.adaptation(currentImg);
        this.neuronList.add(newNeuron);
        System.out.println("Utworzono nowy neuron");
        neuronId = neuronList.indexOf(newNeuron);
        return newNeuron;

    }

    @Override
    public String toString() {return "Network{neuron_list=" + neuronList + "}";}

    public float getP() {
        return p;
    }

    public float getSimilarity() {
        return similarity;
    }

    public int getNeuronId() {
        return neuronId;
    }

    public ArrayList<Neuron> getNeuronList() {
        return neuronList;
    }
}
