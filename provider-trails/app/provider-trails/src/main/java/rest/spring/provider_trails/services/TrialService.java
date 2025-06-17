package rest.spring.provider_trails.services;

import java.util.Set;

import rest.spring.provider_trails.model.Trail;

public interface TrialService {

  Set<Trail> getAllTrails();
}
