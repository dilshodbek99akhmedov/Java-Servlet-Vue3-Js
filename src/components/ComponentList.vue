<template>
  <div>
    <table class="table  table-borderless table-hover">
      <thead class="bg-dark text-white">
      <tr>
        <th scope="col" style="cursor: pointer">Id</th>
        <th scope="col">Model</th>
        <th scope="col">Color</th>
        <th scope="col">Price</th>
        <th scope="col">Speed</th>
        <th scope="col">Action</th>
      </tr>
      </thead>
      <tbody class="car" v-for="car in cars">
      <tr>
        <td><span>{{ car.id }}</span></td>
        <td><span>{{ car.model }}</span></td>
        <td><span>{{ car.color }}</span></td>
        <td><span>{{ car.price }}</span> <b>$</b></td>
        <td><span>{{ car.speed }}</span></td>
        <td class="d-flex justify-content-start">
          <button class="btn btn-warning" @click="$emit('remove', car)">❌</button>
          <span style="width: 12px"></span>
          <button class="btn btn-dark" @click="$emit('edit', car)">️✏️</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    cars: {
      type: Array,
      required: true
    }
  }
}
</script>

<style>

</style>