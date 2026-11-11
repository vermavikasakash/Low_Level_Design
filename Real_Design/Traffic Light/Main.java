class Main {
    static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight(); //initial signal state is RED

        trafficLight.transition(); // RED → GREEN
        trafficLight.transition(); // GREEN → YELLOW
        trafficLight.transition(); // YELLOW → RED
    }
}

// 1. common contract
interface TrafficLightState {
    void action(TrafficLight light);
}

// 2. implementation
class RedState implements TrafficLightState {
    public void action(TrafficLight light) {
        light.changeState(new GreenState());
    }
}

class GreenState implements TrafficLightState {
    public void action(TrafficLight light) {
        light.changeState(new YellowState()); // Fixed typo
    }
}

class YellowState implements TrafficLightState { // Fixed typo
    public void action(TrafficLight light) {
        light.changeState(new RedState());
    }
}

// 3. context
class TrafficLight {
    private TrafficLightState state;

    public TrafficLight() {
        this.state = new RedState(); // initial state
    }

    // Trigger the current state's action
    public void transition() {
        this.state.action(this);
    }

    // Overloaded method to accept and update the new state
    public void changeState(TrafficLightState newState) {
        this.state = newState;
    }
}