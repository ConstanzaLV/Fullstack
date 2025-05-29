package com.edutech.content_service.service;

import com.edutech.content_service.repository.ContentRepository;
import com.edutech.content_service.repository.entity.ContentEntity;
import com.edutech.content_service.service.domain.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {
    @Autowired private ContentRepository repository;

    public List<Content> getContents() {
        List<ContentEntity> entities = repository.getAll();
        List<Content> result = new ArrayList<>();
        for (ContentEntity entity : entities) {
            result.add(
                    new Content(
                            entity.getId(),
                            entity.getTitle(),
                            entity.getDescription(),
                            entity.getUrl()
                    )
            );
        }
        return result;
    }

    public Content getContent(Long id) {
        List<ContentEntity> entities = repository.getAll();
        for (ContentEntity entity : entities) {
            if (entity.getId().equals(id)) {
                return new Content(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getDescription(),
                        entity.getUrl()
                );
            }
        }
        return null;
    }

    public boolean save(Content content) {
        ContentEntity existing = repository.getByTitleOrUrl(
                content.getTitle(),
                content.getUrl()
        );
        if (existing == null) {
            ContentEntity entity = new ContentEntity(
                    content.getId(),
                    content.getTitle(),
                    content.getDescription(),
                    content.getUrl()
            );
            repository.save(entity);
            content.setId(entity.getId());
            return true;
        }
        return false;
    }

    public boolean replace(Long id, Content content) {
        ContentEntity found = null;
        List<ContentEntity> entities = repository.getAll();
        for (ContentEntity entity : entities) {
            if (entity.getId().equals(id)) {
                found = entity;
                break;
            }
        }
        if (found != null) {
            ContentEntity updated = new ContentEntity(
                    id,
                    content.getTitle(),
                    content.getDescription(),
                    content.getUrl()
            );
            repository.replace(found, updated);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        ContentEntity found = null;
        List<ContentEntity> entities = repository.getAll();
        for (ContentEntity entity : entities) {
            if (entity.getId().equals(id)) {
                found = entity;
                break;
            }
        }
        if (found != null) {
            repository.delete(found);
            return true;
        }
        return false;
    }
}

