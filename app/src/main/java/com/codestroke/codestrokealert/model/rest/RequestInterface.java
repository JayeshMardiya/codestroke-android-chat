package com.codestroke.codestrokealert.model.rest;

import com.codestroke.codestrokealert.chat.LoginUserResponse;
import com.codestroke.codestrokealert.model.CaseAssessments;
import com.codestroke.codestrokealert.model.CaseAssessmentsResponse;
import com.codestroke.codestrokealert.model.CaseEdResponse;
import com.codestroke.codestrokealert.model.CaseEds;
import com.codestroke.codestrokealert.model.CaseHistories;
import com.codestroke.codestrokealert.model.CaseHistoriesResponse;
import com.codestroke.codestrokealert.model.CaseManagements;
import com.codestroke.codestrokealert.model.CaseManagmentsResponse;
import com.codestroke.codestrokealert.model.CaseRadiologies;
import com.codestroke.codestrokealert.model.CaseRadiologiesResponse;
import com.codestroke.codestrokealert.model.Cases;
import com.codestroke.codestrokealert.model.CasesResponse;
import com.codestroke.codestrokealert.model.Hospital;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RequestInterface {


    @DELETE("cases/{id}/")
    Call<ResponseBody> deleteCase(@Header("Authorization") String authHeader, @Path("id") int case_id);


    @POST("cases/")
    Call<Cases> addCase(@Header("Authorization") String authHeader, @Body Cases cases);


    @GET("hospital_list.json")
    Call<List<Hospital>> getHospitals(@Header("Authorization") String authHeader);


    @PUT("case_histories/{id}/")
    Call<CaseHistories> updateCase(@Header("Authorization") String authHeader, @Path("id") int case_id, @Body CaseHistories caseHistories);


    @PUT("case_assessments/{id}/")
    Call<CaseAssessments> sendCaseAssessments(@Header("Authorization") String authHeader, @Path("id") int case_id, @Body CaseAssessments caseAssessments);

    @GET("cases/")
    Call<CasesResponse> getCases(@Header("Authorization") String authHeader);

    @GET("case_eds/{id}/")
    Call<CaseEdResponse> getCaseEd(@Header("Authorization") String authHeader, @Path("id") int case_id);

    @PUT("case_eds/{id}/")
    Call<CaseEds> updateCaseEd(@Header("Authorization") String authHeader, @Path("id") int case_id, @Body CaseEds caseEds);

    @GET("cases/{id}/")
    Call<CasesResponse> getPatient(@Header("Authorization") String authHeader, @Path("id") int case_id);

    @PUT("cases/{id}/")
    Call<Cases> updatePatientDetails(@Header("Authorization") String authHeader, @Path("id") int case_id, @Body Cases cases);

    @GET("case_histories/{id}/")
    Call<CaseHistoriesResponse> getCaseHistories(@Header("Authorization") String authHeader, @Path("id") int case_id);

    @GET("case_assessments/{id}/")
    Call<CaseAssessmentsResponse> getCaseAssessments(@Header("Authorization") String authHeader, @Path("id") int case_id);


    @PUT("case_assessments/{id}/")
    Call<CaseAssessments> updateCaseAssessment(@Header("Authorization") String authHeader, @Path("id") int case_id, @Body CaseAssessments caseAssessments);


    @GET("case_radiologies/{id}/")
    Call<CaseRadiologiesResponse> getCaseRadiologies(@Header("Authorization") String authHeader, @Path("id") int case_id);

    @PUT("case_radiologies/{id}/")
    Call<CaseRadiologies> updateCaseRadiologies(@Header("Authorization") String authHeader, @Path("id") int case_id, @Body CaseRadiologies caseRadiologies);

    @GET("case_managements/{id}/")
    Call<CaseManagmentsResponse> getCaseManagments(@Header("Authorization") String authHeader, @Path("id") int case_id);

    @PUT("case_managements/{id}/")
    Call<CaseManagements> updateCaseManagements(@Header("Authorization") String authHeader, @Path("id") int case_id, @Body CaseManagements caseManagements);


    //Login Api
    @FormUrlEncoded
    @POST("user/create.php")
    Call<LoginUserResponse> login(
            @Header("Accept")String accept,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("user_role") String userRole,
            @Field("user_type") String userType,
            @Field("user_token") String userToken
    );

}
