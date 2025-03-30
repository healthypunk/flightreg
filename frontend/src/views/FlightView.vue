<template>
    <div class="flight-view">
        <Flight />
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Flight from '../components/Flights.vue';

export default defineComponent({
    name: 'FlightView',
    components: {
        Flight,
    },
});
</script>