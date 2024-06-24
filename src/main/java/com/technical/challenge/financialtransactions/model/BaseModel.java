package com.technical.challenge.financialtransactions.model;

import java.time.LocalDateTime;

public class BaseModel {

    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public BaseModel setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public BaseModel setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}
