<script setup lang="ts">
import HelloWorld from './components/HelloWorld.vue'
import Login from './components/Login.vue'
import Flight from './components/Fligth.vue'
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue';

const router = useRouter()

onMounted(() =>{
  router.push('/flight')
})
</script>


<template>
  <router-view />

</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
