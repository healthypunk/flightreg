<template>
    <div class="login-container">
      <h2>Log in</h2>
  
      <form @submit.prevent="logIn">
        <div class="form-group">
          <label for="email">Email</label>
          <input v-model="email" type="email" id="email" required />
        </div>
  
        <div class="form-group">
          <label for="username">Username</label>
          <input v-model="username" type="text" id="username" required />
        </div>
  
        <div class="form-group">
          <label for="password">Password</label>
          <input v-model="password" type="password" id="password" required />
        </div>
  
        <button type="submit" :disabled="loading">
          {{ loading ? "Logging in..." : "Log in" }}
        </button>
  
        <p v-if="error" class="error-message">{{ error }}</p>
  
        <p class="signup-link">
          Don't have an account? <router-link :to="{ name: 'SignUp' }">Sign Up</router-link>
        </p>
      </form>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const email = ref<string>("");
const username = ref<string>("");
const password = ref<string>("");
const loading = ref<boolean>(false);
const error = ref<string>("");
const router = useRouter();

const logIn = async () => {
  loading.value = true;
  error.value = "";

  try {
    // Prepare the query string parameters
    const params = new URLSearchParams();
    params.append("username", username.value);
    const response = await axios.get("http://localhost:8080/api/users", { params });

    if (response.status === 200) {
      alert(`Login successful! Welcome, ${response.data.username}`);
      sessionStorage.setItem("loggedInUser", JSON.stringify(response.data));
      router.push('/');
    }
  } catch (err) {
    console.error(err);
    error.value = err.response?.data?.message || 'An error occurred. Please try again.';
  } finally {
    loading.value = false;
  }
};
</script>


