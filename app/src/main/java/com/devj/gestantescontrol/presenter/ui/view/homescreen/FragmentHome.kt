package com.devj.gestantescontrol.presenter.ui.view.homescreen



import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import com.devj.gestantescontrol.data.cache.model.DataDateEmbedded
import com.devj.gestantescontrol.data.cache.model.MeasuresEmbedded
import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import com.devj.gestantescontrol.databinding.FragmentHomeBinding
import com.devj.gestantescontrol.domain.model.DataDate
import com.devj.gestantescontrol.domain.model.Measures
import com.devj.gestantescontrol.domain.model.RiskFactor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FragmentHome : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var pregnantDao: PregnantDao
    private  val risk = RiskFactor("Fumadora")
    private val measures =  MeasuresEmbedded(20.0,"17.6")
    private val dataDate = DataDateEmbedded("0/0/0",true,"0/0/0",12,4,"",6,3,"",12,4)

    private val pregnant = PregnantEntity(
        name = "Esta",
        lastName = "Otro",
        age = "16",
        phoneNumber = "1234545",
        notes = "nota",
        photo = "foto",
        riskFactors = listOf(risk),
        measures = measures,
        dataDate = dataDate
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.fab.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                pregnantDao.insertPregnant(pregnant)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}