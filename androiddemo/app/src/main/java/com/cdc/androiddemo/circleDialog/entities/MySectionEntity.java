package com.cdc.androiddemo.circleDialog.entities;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by hupei on 2018/4/20.
 */

public class MySectionEntity extends SectionEntity<PictureTypeEntity> {

    public MySectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySectionEntity(PictureTypeEntity pictureType) {
        super(pictureType);
    }
}
