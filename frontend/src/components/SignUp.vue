<template>
    <div class="signup-container">
      <h2>Sign Up</h2>
  
      <form @submit.prevent="signUp">
        <div class="form-group">
          <label for="formData.email">Email</label>
          <input v-model="email" type="email" id="email" required />
        </div>
  
        <div class="form-group">
          <label for="formData.username">Username</label>
          <input v-model="username" type="text" id="username" required />
        </div>
  
        <div class="form-group">
          <label for="formData.password">Password</label>
          <input v-model="password" type="password" id="password" required />
        </div>
  
        <button type="submit" :disabled="loading">
          {{ loading ? "Signing up..." : "Sign Up" }}
        </button>
  
        <p v-if="error" class="error-message">{{ error }}</p>
  
        <p class="login-link">
          Already have an account? <router-link :to="{ name: 'Login' }">Log In</router-link>
        </p>
      </form>
    </div>
  </template>
  

  <script setup lang="ts">
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  import axios from 'axios';
  
  const email = ref<string>('');
  const username = ref<string>('');
  const password = ref<string>('');
  const loading = ref(false);
  const error = ref<string>('');
  const router = useRouter();
  
  const signUp = async () => {
    loading.value = true;
    try {
      const formData = {
        email: email.value,
        username: username.value,
        password: password.value,
      };
        console.log(email.value)
      const response = await axios.post('http://localhost:8080/api/users', formData, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      });
  
      if (response.status === 200) {
        alert('User created successfully');
        router.push('/login');
      }
    } catch (err) {
      console.error(err);
      error.value = err.response?.data?.message || 'An error occurred. Please try again.';
    } finally {
      loading.value = false;
    }
  };
  </script>
  

