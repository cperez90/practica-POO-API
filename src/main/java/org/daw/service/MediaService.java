package org.daw.service;

import org.daw.model.MediaContent;
import org.daw.model.MediaItem;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MediaService {

    public <T extends MediaItem> List<String> getNames(List<T> items) {
        return items.stream().map(MediaItem::getDisplayName).collect(Collectors.toList());
    }

    public <T extends MediaItem> List<T> filterByNameContains(List<T> items, String name) {
        Predicate<T> containsName = item -> item.getDisplayName().toLowerCase().contains(name.toLowerCase());
        return items.stream().filter(containsName).collect(Collectors.toList());
    }

    public <T extends MediaContent> List<T> filterByNameAndScoreContains(List<T> items,String name, double minScore) {
        Predicate<T> containsName = item -> item.getDisplayName().toLowerCase().contains(name.toLowerCase());
        Predicate<T> minScorePre = item -> item.getScore() >= minScore;
        Predicate<T> combined = containsName.and(minScorePre);
        return items.stream().filter(combined).collect(Collectors.toList());
    }

    public <T extends MediaItem> List<T> filterByPredicate(List<T> items, Predicate<T> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }

    public <T extends MediaItem> List<T> sortByName(List<T> items) {
        return items.stream().sorted().collect(Collectors.toList());
    }

    public <T extends MediaContent> List<T> sortByScoreThenName(List<T> items) {
        return items.stream().sorted((t1,t2)->{
            int scoreCompare = Double.compare(t2.getScore(), t1.getScore());
            return scoreCompare != 0 ? scoreCompare : t1.getDisplayName().compareToIgnoreCase(t2.getDisplayName());
        }).collect(Collectors.toList());
    }

    public <T extends MediaContent> List<String> getHighScoreNames(List<T> items, double minScore) {
        return items.stream().filter(i -> i.getScore() >= minScore).map(MediaContent::getDisplayName).collect(Collectors.toList());
    }

    public <T extends MediaContent> Optional<T> getHighestScoreName(List<T> items) {
        return items.stream().max(Comparator.comparingDouble(T::getScore));
    }

    public <T extends MediaContent> boolean areContentsEqual(T item,T items2) {
        return item.equals(items2);
    }

    public <T extends MediaItem> T getItemByName(List<T> items, String name) {
        if (items == null || name == null) {
            return null;
        }
        return items.stream().filter(item -> item.getDisplayName().toLowerCase().contains(name.toLowerCase())).findFirst().orElse(null);
    }
}
