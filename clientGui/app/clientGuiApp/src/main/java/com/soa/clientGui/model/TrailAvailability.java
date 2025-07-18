package com.soa.clientGui.model;

/*
 * This class is Just Trail with the hint for possible hiking
 * */
public class TrailAvailability {
  private Trail trail;
  private boolean reccommanded;

  public boolean isReccommanded() {
    return reccommanded;
  }

  public Trail getTrail() {
    return trail;
  }

  public void setTrail(Trail trail) {
    this.trail = trail;
  }

  public void setReccommanded(boolean reccommanded) {
    this.reccommanded = reccommanded;
  }

  @Override
  public String toString() {
    return "TrailAvailability [trail=" + trail + ", reccommanded=" + reccommanded + ", isReccommanded()="
        + isReccommanded() + ", getTrail()=" + getTrail() + "]";
  }

}
