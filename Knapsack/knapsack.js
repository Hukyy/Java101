var data = {
    map: {
        weight: 90,
        value: 150
    },
    compass: {
        weight: 130,
        value: 35
    },
    water: {
        weight: 1530,
        value: 200
    },
    sandwich: {
        weight: 500,
        value: 160
    },
    glucose: {
        weight: 150,
        value: 60
    },
    tin: {
        weight: 680,
        value: 45
    },
    banana: {
        weight: 270,
        value: 60
    },
    apple: {
        weight: 390,
        value: 40
    },
    cheese: {
        weight: 230,
        value: 30
    },

    beer: {
        weight: 520,
        value: 10
    },
    "suntan cream": {
        weight: 110,
        value: 70
    },


    camera: {
        weight: 320,
        value: 30
    },

    "T-shirt": {
        weight: 240,
        value: 15
    },

    trousers: {
        weight: 480,
        value: 10
    },

    umbrella: {
        weight: 730,
        value: 40
    },
    "waterproof trousers": {
        weight: 420,
        value: 70
    },

    "waterproof overclothes": {
        weight: 430,
        value: 75
    },

    "note-case": {
        weight: 220,
        value: 80
    },

    "sunglasses": {
        weight: 70,
        value: 20
    },

    "towel": {
        weight: 180,
        value: 12
    },
    "socks": {
        weight: 40,
        value: 50
    },
    "book": {
        weight: 300,
        value: 10
    },
    "notebook": {
        weight: 900,
        value: 1
    },
    "tent": {
        weight: 2000,
        value: 150
    }
}

var population = []
const recombination_probability = 0.9
const population_size = 200
const mutation_probability = 0.5
const max_weight = 5000
const elitism = 0.4

//generate random population of n chromosomes 
function generateChromosomes(originaldata) {
    var data = cloneChromosome(originaldata)
    var keys = Object.keys(data)

    for (var i = 0; i < keys.length; i++) {
        data[keys[i]].active = Math.round(Math.random());
    }

    return data
}


function generatePopulation(data, population_size) {
    while (population_size > 0) {
        var chromosome = generateChromosomes(data)
        population.push(chromosome)
        population_size--;
    }

    return population
}

function cloneChromosome(data) {
    return JSON.parse(JSON.stringify(data))
}

function fitness(data) {
    var value = 0;
    var weight = 0;
    var score = 0;

    for (var element in data) {
        if (data[element].active) {
            value += data[element].value
            weight += data[element].weight
        }
    }
    score = value
    if (weight > max_weight) {
        score -= (weight - max_weight) * 50  //penalty
    }
    return score
}

function sort(population) {
    var sortedPopulation = cloneChromosome(population)
    sortedPopulation.sort(function (a, b) { return fitness(b) - fitness(a) });
    return sortedPopulation
}

function mate(chr1, chr2) {
    var child1 = {}
    var child2 = {}
    var pivot = Math.abs(Math.round(Math.random() * (Object.keys(chr1).length - 1)))
    var i = 0
    for (var element in chr1) {
        if (i < pivot) {
            child1[element] = chr1[element]
            child2[element] = chr2[element]
        }
        else {
            child2[element] = chr1[element]
            child1[element] = chr2[element]
        }
        i++;
    }

    return [child1, child2]
}

function mutate(chromosome) {
    var keys = Object.keys(chromosome)
    var randomIndex = Math.abs(Math.round(Math.random() * keys.length - 1))
    var index = keys[randomIndex]
    chromosome[`${index}`].active = (chromosome[`${index}`].active) ? 0 : 1
}

function getRandomIndex(limit, exclude) {
    var rand = null
    while (rand === null || exclude.includes(rand)) {
        rand = Math.abs(Math.round(Math.random() * limit - 1))
    }

    return rand
}

function kill(population) {
    var target = Math.floor(elitism * population.length);
    while (population.length > target) {
        population.pop();
    }
}


function knapsack(data, population_size) {
    var population = generatePopulation(data, population_size)
    population = sort(population)
    var best = population[0]
    var improvement = 30
    while (improvement > 0) {
        kill(population)
        var newGenLength = population.length

        for (var i = 0; i < recombination_probability * population_size / 2; i++) {
            var randomIndexFirst = Math.abs(Math.round(Math.random() * newGenLength - 1))
            var randomIndexSecond = getRandomIndex(newGenLength, [randomIndexFirst])
            var crossover = mate(population[randomIndexFirst], population[randomIndexSecond])
            population.push(crossover[0])
            population.push(crossover[1])
        }

        exclude = []
        for (var i = 0; i < population.length * mutation_probability; i++) {
            var randomIndex = getRandomIndex(population.length, exclude)
            exclude.push(randomIndex)
            mutate(population[randomIndex])
        }

        population = sort(population)
        var currentBest = population[0]

        if (fitness(currentBest) > fitness(best)) {
            best = cloneChromosome(currentBest)
            improvement = 30
        }
        improvement--;
    }
    console.log(fitness(best))
    return best
}

knapsack(data, population_size)
