import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
    	
    	Faker faker = new Faker();
    	List<Intersection> nodes = IntStream.rangeClosed(0, 9)
    			.mapToObj(i->new Intersection(faker.address().streetName() + " " + faker.address().streetSuffix()))
    			.collect(Collectors.toList());
    	
    	List<Street> streets = new LinkedList<>();
    	streets.add(new Street(faker.address().streetName(), 2.5, nodes.get(0), nodes.get(1)));
    	streets.add(new Street(faker.address().streetName(), 1.2, nodes.get(1), nodes.get(2)));
    	streets.add(new Street(faker.address().streetName(), 3.8, nodes.get(0), nodes.get(2)));
    	streets.add(new Street(faker.address().streetName(), 4.0, nodes.get(2), nodes.get(3)));
    	streets.add(new Street(faker.address().streetName(), 1.5, nodes.get(3), nodes.get(4)));
    	streets.add(new Street(faker.address().streetName(), 2.0, nodes.get(1), nodes.get(4)));
    	
    	City orasulMeu = new City("Pascani",nodes,streets);;
    	
    	Map<Intersection, Long> degreeMap = orasulMeu.getStreets().stream() //gradele nodurilor
    	        .flatMap(s-> Stream.of(s.getStart(), s.getEnd()))
    	        .collect(Collectors.groupingBy(i->i, Collectors.counting()));
    	
    	double valoareSpecificata = 1.5;
    	
    	List<Street> filteredStreets = orasulMeu.getStreets().stream()
    			.filter(s->s.getLength()> valoareSpecificata)
    			.filter(s->{
    				long conexiuniStart = degreeMap.get(s.getStart());
    				long conexiuniEnd = degreeMap.get(s.getEnd());
    				return (conexiuniStart + conexiuniEnd - 2) >=3;
    			})
    			.collect(Collectors.toList());
    	filteredStreets.forEach(System.out::println);
    	
        Graph<Intersection, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        
        for(Intersection i : orasulMeu.getIntersections()) {
        	graph.addVertex(i);
        }
        
        for(Street s : orasulMeu.getStreets()) {
        	DefaultWeightedEdge edge = graph.addEdge(s.getStart(), s.getEnd());
        	if(edge != null)
        		graph.setEdgeWeight(edge, s.getLength());
            }
        KruskalMinimumSpanningTree<Intersection, DefaultWeightedEdge> mstAlgorithm = new KruskalMinimumSpanningTree<> (graph);
        
        System.out.println("Costul minim este: " + mstAlgorithm.getSpanningTree().getWeight());
        System.out.println("Muchiile pe care trebuie trase cablurile: ");
        mstAlgorithm.getSpanningTree().getEdges().forEach(System.out::println);
        
    }
}
