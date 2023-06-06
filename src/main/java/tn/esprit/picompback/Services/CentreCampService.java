package tn.esprit.picompback.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Repositories.CentreCampRepository;

import java.util.List;

@Service
public class CentreCampService implements ICentreCampService{

    @Autowired
    CentreCampRepository centreCampRepository ;

    @Override
    public CentreCamp AjouterCentreCamp(CentreCamp cca) {
        return centreCampRepository.save(cca);
    }

    @Override
    public List<CentreCamp> GetCentreCamps() {
        return centreCampRepository.findAll();
    }

    @Override
    public void updateCentreCamp(CentreCamp cca) {
        centreCampRepository.save(cca) ;
    }

    @Override
    public CentreCamp GetCentreCamp(long id) {
        return centreCampRepository.findById(id).get();
    }

    @Override
    public void DeleteCentreCamp(long id) {
        centreCampRepository.delete(centreCampRepository.findById(id).get());
    }
}
