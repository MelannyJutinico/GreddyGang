package co.edu.unbosque.PayrollAPI;

import co.edu.unbosque.PayrollAPI.repository.INominaRepositoy;
import co.edu.unbosque.PayrollAPI.repository.INovedadRepository;
import co.edu.unbosque.PayrollAPI.repository.IPeriodoNominaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayrollApiApplication implements CommandLineRunner {

    public static void main(String[] args){
		SpringApplication.run(PayrollApiApplication.class, args);
	}

	private final INominaRepositoy nominaRepo;
	private final IPeriodoNominaRepository periodoRepo;
	private final INovedadRepository novedadRepo;

	public PayrollApiApplication(INominaRepositoy nominaRepo, IPeriodoNominaRepository periodoRepo, INovedadRepository novedadRepo) {
		this.nominaRepo = nominaRepo;
		this.periodoRepo = periodoRepo;
        this.novedadRepo = novedadRepo;
    }

	@Override
	public void run(String... args) throws Exception {

	}


}
