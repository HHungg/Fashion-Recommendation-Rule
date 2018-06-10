/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.*;
import model.*;

/**
 *
 * @author HungLM
 */
public class Apriori {

    private ArrayList<Transaction> listTransaction;
    private double minSup;
    private double minConf;

    public Apriori() {
        this.listTransaction = new ArrayList<>();
        this.minConf = 0;
        this.minConf = 0;
    }

    public Apriori(ArrayList<Transaction> listTransaction, double minSup, double minConf) {
        this.listTransaction = listTransaction;
        this.minSup = minSup;
        this.minConf = minConf;
    }

    public ArrayList<Transaction> getListTransaction() {
        return listTransaction;
    }

    public void setListTransaction(ArrayList<Transaction> listTransaction) {
        this.listTransaction = listTransaction;
    }

    public double getMinSup() {
        return minSup;
    }

    public void setMinSup(double minSup) {
        this.minSup = minSup;
    }

    public double getMinConf() {
        return minConf;
    }

    public void setMinConf(double minConf) {
        this.minConf = minConf;
    }

    public ArrayList<Candidate> candiate_gen(FrequentItemset freqItemset) {
        ArrayList<Candidate> listCandidate = new ArrayList<>();
        ArrayList<Candidate> tmpCandidate = new ArrayList<>();
        if (freqItemset.getSize() == 1) {
            for (int i = 0; i < freqItemset.getItemSet().size() - 1; i++) {
                for (int j = i + 1; j < freqItemset.getItemSet().size(); j++) {
                    Candidate tmpC = new Candidate();
                    ArrayList<Fact> tmpF = new ArrayList<>();
                    tmpF.addAll(freqItemset.getItemSet().get(i).getFactSet());
                    tmpF.add(freqItemset.getItemSet().get(j).getFactSet().get(0));
                    tmpC.setFactSet(tmpF);
                    tmpC.setSize(2);
                    listCandidate.add(tmpC);
                    tmpCandidate = new ArrayList<>(listCandidate);
                }
            }
        } else {
            for (int i = 0; i < freqItemset.getItemSet().size() - 1; i++) {
                for (int j = i + 1; j < freqItemset.getItemSet().size(); j++) {
                    for (int k = 0; k < freqItemset.getSize(); k++) {
                        if (!freqItemset.getItemSet().get(i).getFactSet().get(k).getFaceName().equals(freqItemset.getItemSet().get(j).getFactSet().get(k).getFaceName())) {
                            break;
                        }
                        if (k == freqItemset.getSize() - 2) {
                            int z = freqItemset.getItemSet().get(0).getFactSet().size();
                            Candidate tmpC = new Candidate();
                            ArrayList<Fact> tmpF = new ArrayList<>();
                            tmpF.addAll(freqItemset.getItemSet().get(i).getFactSet());
                            tmpF.add(freqItemset.getItemSet().get(j).getFactSet().get(z - 1));
                            tmpC.setFactSet(tmpF);
                            tmpC.setSize(z + 1);
                            listCandidate.add(tmpC);
                        }
                    }
                }
            }

            for (Candidate candidate : listCandidate) {
                int i = 0;
                for (Candidate preCandidate : freqItemset.getItemSet()) {
                    if (candidate.getFactSet().containsAll(preCandidate.getFactSet())) {
                        i++;
                    }
                }
                if (i >= freqItemset.getSize() + 1) {
                    tmpCandidate.add(candidate);
                }
            }
        }
        return tmpCandidate;
    }

    public ArrayList<FrequentItemset> frequent_itemsets(ArrayList<Transaction> T) {
        ArrayList<FrequentItemset> fset = new ArrayList<>();
        FrequentItemset f1 = new FrequentItemset();
        f1.setSize(1);
        for (Transaction t : T) {
            for (Fact f : t.getListFact()) {
                ArrayList<Fact> tmpF = new ArrayList<>();
                tmpF.add(f);
                Candidate tmpC = new Candidate(1, tmpF);
                if (!f1.hasCandidate(tmpC)) {
                    f1.getItemSet().add(tmpC);
                }
            }
        }
        FrequentItemset tmpf1 = new FrequentItemset();
        for (Candidate c : f1.getItemSet()) {
            double i = 0;
            for (Transaction t : T) {
                if (t.hasAllFact(c.getFactSet())) {
                    i++;
                }
            }
            if (i / T.size() >= this.getMinSup()) {
                tmpf1.getItemSet().add(c);
            }
        }
        tmpf1.setSize(1);
        fset.add(tmpf1);
        for (int k = 2; fset.get(k - 2).getItemSet().size() > 1; k++) {
            ArrayList<Candidate> cSet = new ArrayList<>();
            cSet.addAll(candiate_gen(fset.get(k - 2)));
            ArrayList<Candidate> tmpCSet = new ArrayList<>();
            for (Candidate c : cSet) {
                double i = 0;
                for (Transaction t : T) {
                    if (t.hasAllFact(c.getFactSet())) {
                        i++;
                    }
                }
                if (i / T.size() >= this.getMinSup()) {
                    tmpCSet.add(c);
                }
            }
            FrequentItemset tmpF = new FrequentItemset(k, tmpCSet);
            fset.add(tmpF);
        }
        return fset;
    }

    public ArrayList<Suggestion> generate_association_rules(ArrayList<FrequentItemset> fs) {
        ArrayList<Suggestion> suggestions = new ArrayList<>();
        for (FrequentItemset f : fs) {
            for (Candidate c : f.getItemSet()) {
                int n = c.getFactSet().size();
                for (int i = 0; i < (1 << n); i++) {
                    Suggestion tmpS = new Suggestion();
                    for (int j = 0; j < n; j++) {
                        if ((i & (1 << j)) > 0) {
                            tmpS.getLeft().add(c.getFactSet().get(j));
                        }
                    }
                    if (tmpS.getLeft().size() < n && tmpS.getLeft().size() > 0) {
                        Candidate cLeft = new Candidate(tmpS.getLeft().size(), tmpS.getLeft());
                        for (Fact fact : c.getFactSet()) {
                            if (!cLeft.hasFact(fact)) {
                                tmpS.getRight().add(fact);
                            }
                        }
                        Candidate cRight = new Candidate(tmpS.getRight().size(), tmpS.getRight());
                        if (c.calculateSup(this.getListTransaction()) / cLeft.calculateSup(listTransaction) > this.getMinConf()) {
                            suggestions.add(tmpS);
                        }
                    }
                }
            }
        }
        return suggestions;
    }
}
