package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.TagCommand;
import com.minifacebookbackend.domain.model.Tag;
import com.minifacebookbackend.repository.TagRepository;
import com.minifacebookbackend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag saveTag(TagCommand tagCommand) {
        if(tagCommand == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag to save is null");
        }
        Tag tagToSave = new Tag();
        tagToSave.setContent(tagCommand.getContent());
        tagToSave.setPostId(tagCommand.getPostId());
        return tagRepository.save(tagToSave);
    }
    @Override
    public List<Tag> saveTags(List<TagCommand> tagCommands){
        List<Tag> tags=new ArrayList<>();
        for (TagCommand tagCommand : tagCommands) {
            tags.add(saveTag(tagCommand));
        }
        return tags;
    }

    @Override
    public Tag updateTag(TagCommand tagCommand){
        if(tagCommand.isNew()){
           return saveTag(tagCommand);
        }else {
            Tag tag=tagRepository.findById(tagCommand.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag id is not valid"));
            tag.setContent(tagCommand.getContent());
            tag.setPostId(tagCommand.getPostId());
            return tagRepository.save(tag);
        }
    }

    @Override
    public void delete(String tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag id is not valid"));
        tagRepository.delete(tag);
    }
    @Override
    public void deleteTags(List<Tag> tags){
        tagRepository.deleteAll(tags);
    }
    @Override
    public void updateTags(List<TagCommand> tags){
        for (TagCommand tagCommand : tags) {
            updateTag(tagCommand);
        }
    }
}
