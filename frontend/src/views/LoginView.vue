<template>
    <div class="login-view">
        <Login />
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Login from '@/components/Login.vue';

export default defineComponent({
    name: 'LoginView',
    components: {
        Login,
    },
});
</script>

<style scoped>
.login-view {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f5f5f5;
}
</style>