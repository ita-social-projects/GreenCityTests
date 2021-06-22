package com.softserve.edu.greencity.ui.tools.jdbc.services;

import com.softserve.edu.greencity.data.editprofile.EditProfileData;
import com.softserve.edu.greencity.data.editprofile.EditProfileDataRepository;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.EditProfileDao;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.EditProfileSocialNetworksDao;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EditProfileEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EditProfileSocialNetworksEntity;

public class EditProfileService {
    private EditProfileDao editProfileDao;
    private EditProfileSocialNetworksDao editProfileSocialNetworksDao;

    public EditProfileService() {
        editProfileDao = new EditProfileDao();
        editProfileSocialNetworksDao = new EditProfileSocialNetworksDao();
    }

    public void deleteSocialNetworksByUserId(long userId) {
        for (EditProfileSocialNetworksEntity editProfileSocialNetworksEntity
                : editProfileSocialNetworksDao.selectAllByUserId(userId)) {
            editProfileSocialNetworksDao.delete(editProfileSocialNetworksEntity);
        }
    }

    public void updateUserEditProfileToDefaultById(long id) {
        EditProfileEntity editProfileEntity = editProfileDao.selectById(id);
        EditProfileData editProfileData = EditProfileDataRepository.get().getRequiredDefaultFieldsEditProfile();
        editProfileDao.updateNameField(editProfileData, editProfileEntity);
        editProfileDao.updateCityField(editProfileData, editProfileEntity);
        editProfileDao.updateUserCredoField(editProfileData, editProfileEntity);
        deleteSocialNetworksByUserId(id);
    }

    public void updateUserEditProfileToDefaultByEmail(String email) {
        EditProfileEntity editProfileEntity = editProfileDao.selectByEmail(email);
        EditProfileData editProfileData = EditProfileDataRepository.get().getRequiredDefaultFieldsEditProfile();
        editProfileDao.updateNameField(editProfileData, editProfileEntity);
        editProfileDao.updateCityField(editProfileData, editProfileEntity);
        editProfileDao.updateUserCredoField(editProfileData, editProfileEntity);
        deleteSocialNetworksByUserId(editProfileEntity.getId());
    }
}
