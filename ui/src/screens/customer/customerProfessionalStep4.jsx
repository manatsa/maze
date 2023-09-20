import React from 'react';
import AppForm from "../../components/form/AppForm.jsx";
import {Field} from "formik";
import AppFormRadio from "../../components/form/AppFormRadio.jsx";
import {RECUR_PERIOD, YES_NO} from "../../components/Constants.jsx";
import AppFormSelectField from "../../components/form/AppFormSelectField.jsx";
import AppFormCalendar from "../../components/form/AppFormCalendar.jsx";
import AppFormCheckbox from "../../components/form/AppFormCheckbox.jsx";

const CustomerProfessionalStep4 =({initValues, validationSchema, onNextStep, onBack, token})=>{


    return (
        <>
            <div className={'grid'}>
                <AppForm
                    initialValues={initValues}
                    validationSchema={validationSchema}
                    onSubmit={onNextStep}
                    onBack={onBack}
                    isLastStep={true}
                >

                    <Field name={'bookingDate'} as={AppFormCalendar}  label={'Select booking date'} dateFormat={'dd/mm/yy'} />
                    <Field name={'homeService'} as={AppFormCheckbox}  label={'Home Service?'}  />
                    <Field name={'recurring'} as={AppFormCheckbox}  label={'Recurring Appointment?'}  />
                    <Field name={'recurringPeriod'} as={AppFormSelectField} options={RECUR_PERIOD} label={'How often?'} dropdown={true} />

                </AppForm>
            </div>
        </>
    )
}

export default CustomerProfessionalStep4;