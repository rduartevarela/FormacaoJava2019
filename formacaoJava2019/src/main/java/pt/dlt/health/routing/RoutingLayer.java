package pt.dlt.health.routing;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.dlt.health.bl.BusinessLayer;
import pt.dlt.health.dto.Appointment;
import pt.dlt.health.dto.Doctor;
import pt.dlt.health.dto.Patient;

@RestController
public class RoutingLayer {

    Logger LOGGER = Logger.getLogger("RoutingLayer");

    private @Autowired BusinessLayer businessLayer;

    private @Autowired MeterRegistry meterRegistry;
    
    @GetMapping("/doctors")
    public List<Doctor> getListDoctor() {
        LOGGER.info("BusinessLayer.getListDoctor()");

        Counter testCounter = this.meterRegistry.counter("test.counter", "path", "/doctors", "name", "test");
        testCounter.increment(1.0);

        return businessLayer.getListDoctor();
    }

    @GetMapping("/patients")
    public List<Patient> getListPatient() {
        LOGGER.info("BusinessLayer.getListPatient()");

        Counter testCounter = this.meterRegistry.counter("test.counter", "path", "/patients", "name", "test" );
        testCounter.increment(1.0);

        return businessLayer.getListPatient();
    }

    @GetMapping("/appointments")
    public List<Appointment> getListAppointment() {
        LOGGER.info("BusinessLayer.getListAppointment()");

        Counter testCounter = this.meterRegistry.counter("test.counter", "path", "/appointments", "name", "test" );
        testCounter.increment(1.0);

        return businessLayer.getListAppointment();
    }

    @Scheduled(fixedRate = 1000)
    public void incrementCounter() {
        Counter testCounter = null;
        int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
        switch(randomNum) {
            case 1:
                testCounter = this.meterRegistry.counter("test.counter", "path", "/appointments", "name", "test" );
                testCounter.increment(1.0);
                break;
            case 2:
                testCounter = this.meterRegistry.counter("test.counter", "path", "/patients", "name", "test" );
                testCounter.increment(2.0);
                break;
            default:
                testCounter = this.meterRegistry.counter("test.counter", "path", "/doctors", "name", "test");
                testCounter.increment(3.0);
                break;
        }
    }

}
