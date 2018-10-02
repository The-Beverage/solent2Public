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
    private Animal currAnimal;
    
    public List<Animal> getAllAnimals() {
        for (Animal a : animalList) {
            System.out.println("animal '" + a.getName()
                    + "' makes this sound '" + a.getSound()
                    + "' because it is a '" + a.getClass().getSimpleName()
                    + "' implemented by " + a.getClass().getTypeName());
        }
        return animalList;
    }

    public void addDog(String name) {
        currAnimal = AnimalObjectFactory.createDog();
        animalList.add(currAnimal);
        currAnimal.setName(name);
    }

    public void addCat(String name) {
        currAnimal = AnimalObjectFactory.createCat();
        animalList.add(currAnimal);
        currAnimal.setName(name);
    }

    public void addCow(String name) {
        currAnimal = AnimalObjectFactory.createCow();
        animalList.add(currAnimal);
        currAnimal.setName(name);
    }
    
}
