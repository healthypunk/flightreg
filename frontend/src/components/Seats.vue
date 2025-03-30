<template>
    <div>
        <h1>Available Seats</h1>
        <div v-if="loading">Loading seats...</div>
        <div v-else-if="error">{{ error }}</div>
        <div v-else class="seats-container">
            <button 
                v-for="seat in seats" 
                :key="seat.id" 
                class="seat-button" 
                :class="{ booked: seat.isOccupied }"
                @click="selectSeat(seat)"
                :disabled="seat.booked"
            >
                {{ seat.number }}
            </button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            seats: [],
            loading: true,
            error: null,
        };
    },
    methods: {
        async fetchSeats() {
            try {
                const response = await axios.get('http://localhost:8080/api/seats');
                this.seats = response.data;
            } catch (err) {
                this.error = err.message;
            } finally {
                this.loading = false;
            }
        },
        selectSeat(seat) {
            alert(`You selected seat ${seat.number}`);
        },
    },
    mounted() {
        this.fetchSeats();
    },
};
</script>

<style>
.seats-container {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}

.seat-button {
    padding: 10px 20px;
    border: 1px solid #ccc;
    background-color: #f0f0f0;
    cursor: pointer;
}

.seat-button.booked {
    background-color: #d3d3d3;
    cursor: not-allowed;
}

.seat-button:hover:not(.booked) {
    background-color: #e0e0e0;
}
</style>
