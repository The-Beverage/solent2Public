package org.solent.test.project.model;

public interface RestInterface {

    public ReplyMessage retrieveMatchingEntites(Entity entityTempate);
    
    public ReplyMessage retrieveEntity(Integer id);
    
}
