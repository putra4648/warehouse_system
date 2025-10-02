<template>
    <div>
        <h1>Dashboard</h1>
        <div class="stats">
            <div class="stat-card">Total Products: 1200</div>
            <div class="stat-card">Inbound Orders: 15</div>
            <div class="stat-card">Outbound Orders: 10</div>
            <div class="stat-card">Warehouses: 3</div>
        </div>
    </div>
</template>
<style scoped>
.stats {
    display: flex;
    gap: 1rem;
}

.stat-card {
    background: #f5f5f5;
    padding: 1rem;
    border-radius: 8px;
}
</style>