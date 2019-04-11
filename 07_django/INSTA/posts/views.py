from django.shortcuts import render, redirect, get_object_or_404
from django.views.decorators.http import require_http_methods
from .forms import PostModelForm
from .models import Post


@require_http_methods(['GET'])
def post_list(request):
    posts = Post.objects.all()
    return render(request, 'posts/list.html', {
        'posts': posts,
    })


@require_http_methods(['GET', 'POST'])
def create_post(request):
    if request.method == 'POST':
        form = PostModelForm(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            return redirect('posts:post_list')
    else:
        form = PostModelForm()
    return render(request, 'posts/form.html', {
        'form': form,
    })


@require_http_methods(['GET', 'POST'])
def update_post(request, post_id):
    post = get_object_or_404(Post, id=post_id)
    if request.method == 'POST':
        form = PostModelForm(request.POST, instance=post)
        if form.is_valid():
            form.save()
            return redirect('posts:post_list')
    else:
        form = PostModelForm(instance=post)
    return render(request, 'posts/form.html', {
        'form': form,
    })
