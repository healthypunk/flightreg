<template>
    <div class="seat-view">
        <Seats />
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Seats from '../components/Seats.vue';

export default defineComponent({
    name: 'SeatsView',
    components: {
        Seats,
    },
});
</script>