<template>
    <div>
        <h1>Flights</h1>
        <ul>
            <li v-for="flight in flights" :key="flight.id">
                {{ flight.departurePoint }} - {{ flight.destination }}
                <button @click="showSeats(flight.id)">View Seats</button>
            </li>
        </ul>
        
        <Seat v-if="selectedFlightId !== null" :flightId="selectedFlightId" @close="selectedFlightId = null" />
    </div>
</template>


<script setup lang="ts">
import { ref, onMounted } from "vue";
import axios from "axios";

const flights = ref<Array<{ id: number; name: string; destination: string }>>([]);
const loading = ref<boolean>(false);
const error = ref<string>("");

const fetchFlights = async () => {
  loading.value = true;
  error.value = "";

  try {
    const response = await axios.get("http://localhost:8080/api/flights");
    flights.value = response.data;
    console.log(response.data)
  } catch (err) {
    console.error(err);
    error.value = err.response?.data?.message || "Failed to load flights. Please try again.";
  } finally {
    loading.value = false;
  }
};
onMounted(fetchFlights);
</script>

<style scoped>
h1 {
  font-size: 24px;
  margin-bottom: 16px;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 8px 0;
}
</style>