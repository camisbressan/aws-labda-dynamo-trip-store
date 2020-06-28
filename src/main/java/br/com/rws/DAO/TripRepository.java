package br.com.rws.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
 
import br.com.rws.models.Trip;

public class TripRepository {

	private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

	public Trip save(final Trip trip) {
		mapper.save(trip);
		return trip;
	}

	public List<Trip> findByPeriod(final String trip, final String starts, final String ends) {


		  final Map<String, AttributeValue> eav = new HashMap<String,
		  AttributeValue>(); eav.put(":val1", new AttributeValue().withS(trip));
		  eav.put(":val2", new AttributeValue().withS(starts)); eav.put(":val3", new
		  AttributeValue().withS(ends));

		  final DynamoDBQueryExpression<Trip> queryExpression = new
		  DynamoDBQueryExpression<Trip>()
		  .withKeyConditionExpression("trip = :val1 and dateTimeCreation between :val2 and :val3"
		  ) .withExpressionAttributeValues(eav);

		  final List<Trip> studies = mapper.query(Trip.class, queryExpression);

		  return studies;


		 
	}

	public List<Trip> findByCity(final String trip, final String city) {


		  final Map<String, AttributeValue> eav = new HashMap<String,
		  AttributeValue>(); eav.put(":val1", new AttributeValue().withS(trip));
		  eav.put(":val2", new AttributeValue().withS(city));

		  final DynamoDBQueryExpression<Trip> queryExpression = new
		  DynamoDBQueryExpression<Trip>()
		  .withIndexName("tagIndex").withConsistentRead(false)
		  .withKeyConditionExpression("topic = :val1 and tag=:val2").
		  withExpressionAttributeValues(eav);

		  final List<Trip> trips = mapper.query(Trip.class, queryExpression);

		  return trips;


	}

	public List<Trip> findByCountry(final String trip, final String country) {


		  final Map<String, AttributeValue> eav = new HashMap<String,
		  AttributeValue>(); eav.put(":val1", new AttributeValue().withS(trip));
		  eav.put(":val2", new AttributeValue().withS(country));

		  final DynamoDBQueryExpression<Trip> queryExpression = new
		  DynamoDBQueryExpression<Trip>()
		  .withIndexName("tagIndex").withConsistentRead(false)
		  .withKeyConditionExpression("topic = :val1 and tag=:val2").
		  withExpressionAttributeValues(eav);

		  final List<Trip> trips = mapper.query(Trip.class, queryExpression);

		  return trips;

	}

}