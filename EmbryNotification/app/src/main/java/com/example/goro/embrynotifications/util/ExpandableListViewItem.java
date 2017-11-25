package com.example.goro.embrynotifications.util;

import java.util.List;

public class ExpandableListViewItem {

   private String title;
   private List<Data> children;

   public void setChildren(List<Data> children) {
      this.children = children;
   }

   public String getTitle() {
      return title;
   }
}