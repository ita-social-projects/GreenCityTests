package com.softserve.edu.greencity.ui.tools.jdbc.services;

import com.softserve.edu.greencity.ui.tools.jdbc.dao.TipsAndTricksDao;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.TipsAndTricksTagsDao;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.TipsAndTricksEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.TipsAndTricksTagsEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TipsAndTricksService {
    private TipsAndTricksDao tipsAndTricksDao;
    private TipsAndTricksTagsDao tipsAndTricksTagsDao;

    public TipsAndTricksService() {
        tipsAndTricksDao = new TipsAndTricksDao();
        tipsAndTricksTagsDao = new TipsAndTricksTagsDao();
    }

    public int getNewsCount() {
        return getAllNews().size();
    }

    public List<TipsAndTricksEntity> getAllNews() {
        return tipsAndTricksDao.selectAll();
    }

    public List<TipsAndTricksEntity> getAllNewsOrderByDate() {
        return tipsAndTricksDao.selectAllOrderByDate();
    }

    public List<TipsAndTricksEntity> getNewsByTitle(String title) {
        return tipsAndTricksDao.selectByTitle(title);
    }

    public TipsAndTricksEntity getNewsById(long id) {
        return tipsAndTricksDao.selectById(id);
    }

    public List<TipsAndTricksEntity> getNewsListById(long id) {
        return tipsAndTricksDao.selectByIdCorrect(id);
    }

    public List<TipsAndTricksTagsEntity> getNewsTagsByTipsAndTricksId(long tipsAndTricksId) {
        return tipsAndTricksTagsDao.selectByTipsAndTricksId(tipsAndTricksId);
    }

    public void deleteNewsTagsEntityByTipsAndTricksId(long tipsAndTricksId) {
        for (TipsAndTricksTagsEntity tipsAndTricksTagsEntity : tipsAndTricksTagsDao.selectByTipsAndTricksId(tipsAndTricksId)) {
            tipsAndTricksTagsDao.delete(tipsAndTricksTagsEntity);
        }
    }

    public void deleteNewsByTitle(String title) {
        for (TipsAndTricksEntity tipsAndTricksEntity : tipsAndTricksDao.selectByTitle(title)) {
            deleteNewsTagsEntityByTipsAndTricksId(tipsAndTricksEntity.getId());
            tipsAndTricksDao.delete(tipsAndTricksEntity);
        }
    }
    public void deleteNewsById(long id) {
        for (TipsAndTricksEntity tipsAndTricksEntity : tipsAndTricksDao.selectByIdCorrect(id)) {
            deleteNewsTagsEntityByTipsAndTricksId(tipsAndTricksEntity.getId());
            tipsAndTricksDao.delete(tipsAndTricksEntity);
        }
    }
    public long getLastNewsIdByTitle(String title) {
        TipsAndTricksService tipsAndTricksService = new TipsAndTricksService();
        List<Long> listId = new ArrayList<>();
        for (TipsAndTricksEntity tipsAndTricksEntity : tipsAndTricksService.getNewsByTitle(title)) {
            listId.add(tipsAndTricksEntity.getId());
        }
        Collections.sort(listId, Collections.reverseOrder());
        return listId.get(0);
    }

    public String getCreationDate(long id) {
        String date = null;
        TipsAndTricksService tipsAndTricksService = new TipsAndTricksService();
        for (TipsAndTricksEntity tipsAndTricksEntity : tipsAndTricksService.getNewsListById(id)) {
            date= tipsAndTricksEntity.getCreationDate();
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
