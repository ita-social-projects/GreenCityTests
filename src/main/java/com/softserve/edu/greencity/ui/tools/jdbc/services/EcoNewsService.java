package com.softserve.edu.greencity.ui.tools.jdbc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.softserve.edu.greencity.ui.tools.jdbc.dao.EcoNewsDao;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.EcoNewsTagsDao;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsTagsEntity;

public class EcoNewsService {
    private EcoNewsDao ecoNewsDao;
    private EcoNewsTagsDao ecoNewsTagsDao;
    
    public EcoNewsService() {
        ecoNewsDao = new EcoNewsDao();
        ecoNewsTagsDao = new EcoNewsTagsDao();
    }
    
    public int getNewsCount() {
        return getAllNews().size();
    }
    
    public List<EcoNewsEntity> getAllNews() {
        return ecoNewsDao.selectAll();
    }
    public List<EcoNewsEntity> getAllNewsOrderByDate() {
        return ecoNewsDao.selectAllOrderByDate();
    }

    public List<EcoNewsEntity> getNewsByTitle(String title) {
        return ecoNewsDao.selectByTitle(title);
    }
    
    public EcoNewsEntity getNewsById(long id) {
        return ecoNewsDao.selectById(id);
    }

    public List<EcoNewsEntity> getNewsListById(long id) {
        return ecoNewsDao.selectByIdCorrect(id);
    }
    
    public List<EcoNewsTagsEntity> getNewsTagsByEcoNewsId(long ecoNewsId) {
        return ecoNewsTagsDao.selectByEcoNewsId(ecoNewsId);
    }
    
    public void deleteNewsTagsEntityByEcoNewsId(long ecoNewsId) {
        for (EcoNewsTagsEntity ecoNewsTagsEntity : ecoNewsTagsDao.selectByEcoNewsId(ecoNewsId)) {
            ecoNewsTagsDao.delete(ecoNewsTagsEntity);
        }
    }
    
    public void deleteNewsByTitle(String title) {
        for (EcoNewsEntity ecoNewsEntity : ecoNewsDao.selectByTitle(title)) {
            deleteNewsTagsEntityByEcoNewsId(ecoNewsEntity.getId());
            ecoNewsDao.delete(ecoNewsEntity);
        }
    }
    public void deleteNewsById(long id) {
        for (EcoNewsEntity ecoNewsEntity : ecoNewsDao.selectByIdCorrect(id)) {
            deleteNewsTagsEntityByEcoNewsId(ecoNewsEntity.getId());
            ecoNewsDao.delete(ecoNewsEntity);
        }
    }
    public long getLastNewsIdByTitle(String title) {
        EcoNewsService ecoNewsService = new EcoNewsService();
        List<Long> listId = new ArrayList<>();
        for (EcoNewsEntity ecoNewsEntity : ecoNewsService.getNewsByTitle(title)) {
            listId.add(ecoNewsEntity.getId());
        }
        Collections.sort(listId, Collections.reverseOrder());
        return listId.get(0);
    }

    public String getCreationDate(long id) {
        String date = null;
        EcoNewsService ecoNewsService = new EcoNewsService();
        for (EcoNewsEntity ecoNewsEntity : ecoNewsService.getNewsListById(id)) {
            date= ecoNewsEntity.getCreationDate();
        }
        return date;
    }

    public Date getLastNewsCreationDateByTitle(String title){
        Date date = null;
        String creationDate =getCreationDate(getLastNewsIdByTitle(title)).split("\\.")[0];
        try {
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(creationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public void deleteLastNewsByTitle(String title) {
       deleteNewsById(getLastNewsIdByTitle(title));
    }
}
