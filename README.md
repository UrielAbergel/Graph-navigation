
![כותרת](https://user-images.githubusercontent.com/54840897/71785482-aedfde00-3008-11ea-8666-ce053db6bc18.PNG)

![tttt](https://user-images.githubusercontent.com/54840897/71785180-4cd1a980-3005-11ea-82e1-96c277ee72fd.jpg)

# descreption :
In this project we implemented the class graph,
According to the implementaion, each graph has vertices and edges
In this project we realized the vertices as "Road junction" And all edges as roads, any road can go two-way, with a direction marked by a red circle on the edge This graph shows the shortest ways from point to point, and the rest of the detail in WIKI

![גרף רגיל](https://user-images.githubusercontent.com/54840897/71784225-b64bbb00-2ff9-11ea-8598-92e4059b0897.PNG)


# Class

## NodeData : 

### descreption :
Represents a single vertex in the graph receives an id and receives the point it sits on in the graph (x, y)

### Methods :
- getKey : Returns the node ID
- getLocation : Returns Point3D that contains x, y, z
- getWeight : Returns the "weight" of the node
- getInfo : Returns the values ​​that exist in the node as a string
- getTag : Represented in class as 1 or 0 and used if you want to mark a node
Each method has a set method Represented by set "value"


## EdgeData : 

### descreption :
Represents a one-way or two-way road by the description above , Need two to create an edge

### Methods :
- getSrc : Returns the value of the source (INT value)
- getDest : Returns the value of the destination (INT value)
- getWeight : Returns the "weight" of the edge
- getInfo : Returns the values ​​that exist in the edge as a string
- getTag : Represented in class as 1 or 0 and used if you want to mark a edge
Each method has a set method Represented by set "value"


## Dgraph : 

### descreption :
Represents a full graph with vertices and edges, with each graph describing a road system ת Where each vertex is a road intersection and each edge is the road

### Methods :
- getNode : Returns the vertex by its ID
- getEdge : Returns the edge by its src and its dest
- getWeight : Returns the edge Weight by its src and its dest
- connect : Creates a new edge between one node and the other that represents a road
- getV : Returns an ArrayList that represents all the graph vertices
- getE : Returns an ArrayList that represents all the graph Edges
- removeNode 
- removeEdge
- nodeSize
- edgeSize
- getMC : Returns the Mode Counter of the graph



## Graph_Algo : 

### descreption :
Represents the entire graph algorithm, including the short way from road intersection to road intersection, with each Edge having a weight that represents traffic time.There is also an option to check a journey between several nodes and return the shortest way to get through all of them


### Methods :
- init : Load graph to algorithm
- save : Saves the graph with the algorithm in a CSV file
- init(String) : Load graph to algorithm from CSV file
- isConnected : Checks if a graph is strongly Connected

### Navigation algorithm :

#### shortestPathDist : 
Checks the shortest way from apex to apex and returns an int value, where the same value is the trip between edges.
The algorithm uses the Diaxtra source algorithm

#### Example shortestPathDist GUI:
![תמונה אמיתית](https://user-images.githubusercontent.com/54840897/71784890-87d1de00-3001-11ea-97df-35895d079556.PNG)

#### shortestPath : 
Checks the shortest way from apex to apex and returns an int value, where the same value is the trip between edges.
The algorithm uses the Diaxtra source algorithm ,The algorithm returns the short path between a vertex and a vertex when it returns a node_data LIST

#### Example shortestPath GUI:
![דוגמא](https://user-images.githubusercontent.com/54840897/71784979-cc11ae00-3002-11ea-9692-9850e65e0566.PNG)

#### TSP - travel salesman : 
The algorithm gets a list of the vertices to pass through, the algorithm returns the shortest way to go through all the vertices, and stay the shortest time from place to place , The algorithm returns a list of vertices that are its overall trajectory

#### Example TSP:
![דוגמא 2](https://user-images.githubusercontent.com/54840897/71785116-87871200-3004-11ea-9cbc-b512938c1d45.PNG)

