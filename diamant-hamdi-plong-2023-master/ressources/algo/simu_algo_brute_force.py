from itertools import permutations
import numpy as np

# N.B: intesity = time to extinguish the fire
fires = [
    {"location": (10, 10), "intensity": 5},
    {"location": (20, 5), "intensity": 7},
    {"location": (15, 15), "intensity": 4},
]

fire_station_location = (0, 0)

def calculate_total_time(sequence):
    total_time = 0
    current_location = fire_station_location
    for fire_index in sequence:
        fire = fires[fire_index]
        # Travel time to the next fire
        travel_time = np.linalg.norm(np.array(current_location) - np.array(fire["location"]))
        # Add travel time and extinguishing time
        total_time += travel_time + fire["intensity"]
        current_location = fire["location"]
   
    return total_time

sequences = permutations(range(len(fires)))

#finding the optimal sequence
min_time = float('inf')
optimal_sequence = None
for sequence in sequences:
    total_time = calculate_total_time(sequence)
    if total_time < min_time:
        min_time = total_time
        optimal_sequence = sequence

optimal_sequence, min_time
