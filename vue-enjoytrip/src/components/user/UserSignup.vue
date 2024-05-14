<template>
    <div class="d-flex flex-column">
        <div class="flex-shrink-0 container px-5 py-5">
            <div class="py-5 px-4 px-md-5 mb-5">
                
                <!-- title -->
                <h1 class="fw-bolder text-center mb-5">회원가입</h1>
                
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6">
                        <form id="signupForm" method="post" action="${root}/user/signup">
                            <!-- <input type="hidden" name="action" value="signup"> -->
                            <!--contactForm-->

                            <!-- Name input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" name="name" type="text"
                                    placeholder="Enter your name..." data-sb-validations="required" />
                                <label for="name">이름</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    A name is required.
                                </div>
                            </div>

                            <!-- ID input -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" name="id" type="text"
                                    placeholder="Enter your name..." data-sb-validations="required" />
                                <label for="name">아이디</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    A name is required.
                                </div>
                            </div>

                            <!-- PWD input -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="pwd" name="pwd" type="password"
                                    placeholder="Enter your name..." data-sb-validations="required" />
                                <label for="name">비밀번호</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    A name is required.
                                </div>
                            </div>

                            <!-- PWD recheck input -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="pwdCheck" name="pwdcheck" type="password"
                                    placeholder="Enter your name..." data-sb-validations="required" />
                                <label for="name">비밀번호 확인</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    A name is required.
                                </div>
                            </div>

                            <!-- Email address input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="email" name="email" type="email"
                                    placeholder="name@example.com" data-sb-validations="required,email" />
                                <label for="email">이메일</label>
                                <div class="invalid-feedback" data-sb-feedback="email:required">
                                    An email is required.
                                </div>
                                <div class="invalid-feedback" data-sb-feedback="email:email">
                                    Email is not valid.
                                </div>
                            </div>

                            <!-- Submit Button-->
                            <div class="d-grid">
                                <input class="btn btn-lg btn-dark" id="submitButton" type="submit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>