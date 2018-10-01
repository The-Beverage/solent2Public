/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3moulj69
 */
public class FarmFacadeImpl implements FarmFacade{

    private List<Animal> animalList = new ArrayList<Animal>();
    
    public List<Animal> getAllAnimals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addDog(String name) {
        animalList.add(AnimalObjectFactory.createDog());
    }

    public void addCat(String name) {
        animalList.add(AnimalObjectFactory.createCat());
    }

    public void addCow(String name) {
        animalList.add(AnimalObjectFactory.createCow());
    }
    
}
