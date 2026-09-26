package com.lucas.Tipmanager.service;

import com.lucas.Tipmanager.entity.WorkDay;
import com.lucas.Tipmanager.exception.NoEmployeesWorkedException;
import com.lucas.Tipmanager.repository.WorkDayRepository;
import com.lucas.Tipmanager.repository.WorkedDayRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.lucas.Tipmanager.exception.WorkDayAlreadyClosedException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkDayServiceTest {

    @Mock
    private WorkDayRepository workDayRepository;

    @Mock
    private WorkedDayRepository workedDayRepository;

    @InjectMocks
    private WorkDayService workDayService;

    @Test
    void shouldNotCloseWorkDayWithoutEmployees() {

        Long id = 1L;

        WorkDay workDay = new WorkDay();

        when(workDayRepository.findById(id))
                .thenReturn(Optional.of(workDay));

        when(workedDayRepository.findByWorkDayId(id))
                .thenReturn(java.util.List.of());

        assertThrows(
                NoEmployeesWorkedException.class,
                () -> workDayService.close(id)
        );

        verify(workDayRepository).findById(id);
        verify(workedDayRepository).findByWorkDayId(id);
    }

    @Test
    void shouldNotCloseAlreadyClosedWorkDay() {

        Long id = 1L;

        WorkDay workDay = new WorkDay();
        workDay.setClosed(true);

        when(workDayRepository.findById(id))
                .thenReturn(Optional.of(workDay));

        assertThrows(
                WorkDayAlreadyClosedException.class,
                () -> workDayService.close(id)
        );

        verify(workDayRepository).findById(id);
        verify(workedDayRepository, never()).findByWorkDayId(id);
    }
    @Test
    void shouldCalculateTipsCorrectlyWhenClosingWorkDay() {

        Long id = 1L;

        WorkDay workDay = new WorkDay();

        workDay.setTotalTip(new java.math.BigDecimal("123.47"));
        workDay.setCarryOverIn(java.math.BigDecimal.ZERO);
        workDay.setClosed(false);

        java.util.List<com.lucas.Tipmanager.entity.WorkedDay> workedDays =
                new java.util.ArrayList<>();

        for (int i = 0; i < 4; i++) {
            workedDays.add(
                    new com.lucas.Tipmanager.entity.WorkedDay()
            );
        }

        when(workDayRepository.findById(id))
                .thenReturn(Optional.of(workDay));

        when(workedDayRepository.findByWorkDayId(id))
                .thenReturn(workedDays);

        when(workedDayRepository.saveAll(workedDays))
                .thenReturn(workedDays);

        when(workDayRepository.save(workDay))
                .thenReturn(workDay);

        WorkDay result = workDayService.close(id);

        assertEquals(
                new java.math.BigDecimal("20.50"),
                workedDays.get(0).getAmountReceived()
        );

        assertEquals(
                new java.math.BigDecimal("41.00"),
                result.getKitchenAmount()
        );

        assertEquals(
                new java.math.BigDecimal("0.47"),
                result.getCarryOverOut()
        );

        assertTrue(result.getClosed());

        verify(workedDayRepository).saveAll(workedDays);
        verify(workDayRepository).save(workDay);
    }

    @Test
    void shouldIncludeCarryOverWhenCalculatingTips() {

        Long id = 1L;

        WorkDay workDay = new WorkDay();

        workDay.setTotalTip(new java.math.BigDecimal("100.00"));
        workDay.setCarryOverIn(new java.math.BigDecimal("0.47"));
        workDay.setClosed(false);

        java.util.List<com.lucas.Tipmanager.entity.WorkedDay> workedDays =
                new java.util.ArrayList<>();

        for (int i = 0; i < 4; i++) {
            workedDays.add(
                    new com.lucas.Tipmanager.entity.WorkedDay()
            );
        }

        when(workDayRepository.findById(id))
                .thenReturn(Optional.of(workDay));

        when(workedDayRepository.findByWorkDayId(id))
                .thenReturn(workedDays);

        when(workedDayRepository.saveAll(workedDays))
                .thenReturn(workedDays);

        when(workDayRepository.save(workDay))
                .thenReturn(workDay);

        WorkDay result = workDayService.close(id);

        assertEquals(
                new java.math.BigDecimal("16.70"),
                workedDays.get(0).getAmountReceived()
        );

        assertEquals(
                new java.math.BigDecimal("33.40"),
                result.getKitchenAmount()
        );

        assertEquals(
                new java.math.BigDecimal("0.27"),
                result.getCarryOverOut()
        );

        assertTrue(result.getClosed());
    }

}