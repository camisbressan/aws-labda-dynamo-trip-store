package br.com.rws.handler;

import br.com.rws.DAO.TripRepository;
import br.com.rws.models.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.rws.models.HandlerRequest;
import br.com.rws.models.HandlerResponse;

import java.util.List;

public class GetTripsByCountry implements RequestHandler<HandlerRequest, HandlerResponse>{

	private final TripRepository repository = new TripRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		final String trip = request.getPathParameters().get("trip");
		final String country = request.getPathParameters().get("country");

		context.getLogger().log("Buscano por viagems " + trip + " e país = " + country);

		final List<Trip> trips = this.repository.findByCity(trip, country);

		if (trips == null || trips.isEmpty()) {
			return HandlerResponse.builder().setStatusCode(404).build();
		}

		return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();


	}

}