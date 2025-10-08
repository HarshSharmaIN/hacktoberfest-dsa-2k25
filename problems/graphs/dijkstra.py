import heapq

def dijkstra(graph, start):
    """
    Implements Dijkstra's algorithm to find the shortest path from a starting node
    to all other nodes in a weighted graph.

    Args:
        graph (dict): A dictionary representing the graph where keys are nodes
                      and values are dictionaries of {neighbor: weight}.
                      Example: {'A': {'B': 1, 'C': 4}, 'B': {'D': 5}}
        start (str/int): The starting node.

    Returns:
        dict: A dictionary containing the shortest distance from the start node
              to every other node.
    """
    # Initialize distances: 0 for the start node, infinity for all others
    distances = {node: float('inf') for node in graph}
    distances[start] = 0

    # Priority queue (min-heap) stores tuples: (distance, node)
    priority_queue = [(0, start)]

    while priority_queue:
        # Get the node with the smallest known distance
        current_distance, current_node = heapq.heappop(priority_queue)

        # If the extracted distance is greater than the recorded distance, skip (stale entry)
        if current_distance > distances[current_node]:
            continue

        # Explore neighbors
        for neighbor, weight in graph[current_node].items():
            distance = current_distance + weight

            # Relaxation step: If a shorter path to the neighbor is found
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                # Add the neighbor to the queue with its new, shorter distance
                heapq.heappush(priority_queue, (distance, neighbor))

    return distances

# Example Usage: For testing purposes
# graph = {
#     'A': {'B': 1, 'C': 4},
#     'B': {'A': 1, 'C': 2, 'D': 5},
#     'C': {'A': 4, 'B': 2, 'D': 1},
#     'D': {'B': 5, 'C': 1}
# }
# shortest_paths = dijkstra(graph, 'A')
# print(shortest_paths)
